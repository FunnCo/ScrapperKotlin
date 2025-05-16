package org.example.scrapper.parser.impl

import org.example.scrapper.external.webdriver.WebDriver
import org.example.scrapper.parser.Parser
import org.example.scrapper.service.ScrapperService

class MegafonParser(
    scrapperService: ScrapperService,
    name: String,
    baseUrl: String,
    private val webDriver: WebDriver,
    private val currentApiKey: String
) :
    Parser(scrapperService, name, baseUrl) {
    override fun parse(): String {
        webDriver.navigateTo(baseUrl)
        imitateWork()
        println("$logPrefix failed to open, requesting new proxy (old: $currentProxyAddress:$currentProxyPort)")
        renewProxy()
        imitateWork()
        println("$logPrefix failed to open, requesting new proxy (old: $currentProxyAddress:$currentProxyPort)")
        renewProxy()
        imitateWork()
        println("$logPrefix Parsed successfully using API key: $currentApiKey")
        println("$logPrefix final config: " + webDriver.getCurrentPage() + " " + webDriver.getSignature())
        return "$name parsed data"
    }
}