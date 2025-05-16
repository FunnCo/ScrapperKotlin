package org.example.scrapper.service

import org.example.scrapper.external.captcha.CaptchaService
import org.example.scrapper.external.proxy.ProxyProvider
import org.example.scrapper.external.webdriver.WebDriverProvider
import org.example.scrapper.external.webdriver.WebDriverType
import org.example.scrapper.parser.Parser
import org.example.scrapper.parser.impl.BeelineParser
import org.example.scrapper.parser.impl.MegafonParser
import org.example.scrapper.parser.impl.Tele2Parser
import kotlin.random.Random

// Медиатор он же посредник
class ScrapperService {
    private var parserList: List<Parser> = emptyList()

    fun parseCustom(url: String, name: String): String {
        val parser = object : Parser(this, name, url) {
            override fun parse(): String {
                println("$logPrefix Parsing $name without predefined strategy")
                while (true) {
                    imitateWork()
                    when (Random.nextInt(1, 11)) {
                        in 1..2 -> {
                            println("$logPrefix failed to open, requesting new proxy (old: $currentProxyAddress:$currentProxyPort)")
                            renewProxy()
                        }
                        in 3..6 -> println("$logPrefix failed to parse, retrying")
                        else -> break
                    }
                }
                println ("$logPrefix Parsed successfully")
                return "$name parsed data"
            }
        }
        return parser.parse()
    }

    fun parseAllPredefined(): String {
        if (parserList.isEmpty()) {
            initParsers()
        }
        return parserList.map { parser: Parser -> parser.parse() }.joinToString("; ")
    }

    private fun initParsers() {
        val tempList = mutableListOf<Parser>()

        val beelineParser = BeelineParser(
            this,
            "Beeline",
            "https://moskva.beeline.ru/customers/products/",
            WebDriverProvider.getWebdriver(WebDriverType.CHROME, null)
        )
        tempList.add(beelineParser)

        val predefinedProxy = ProxyProvider.getRandomProxy()
        val megafonParser = MegafonParser(
            this,
            "Megafon",
            "https://spb.megafon.ru",
            WebDriverProvider.getWebdriver(WebDriverType.FIREFOX, "81", predefinedProxy.first, predefinedProxy.second),
            "PseudoSecretAPIKey"
        )
        tempList.add(megafonParser)

        val tele2Parser = Tele2Parser(
            this,
            "Tele2",
            "https://spb.t2.ru"
        )
        tempList.add(tele2Parser)

        parserList = tempList.toList()
    }

    fun getNewProxy(): Pair<String, String> {
        return ProxyProvider.getRandomProxy()
    }

    fun handleCaptcha(currentPage: String): Boolean {
        return CaptchaService.resolveCaptcha(currentPage)
    }
} 