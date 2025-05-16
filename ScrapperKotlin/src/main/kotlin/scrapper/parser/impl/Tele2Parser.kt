package org.example.scrapper.parser.impl

import org.example.scrapper.parser.Parser
import org.example.scrapper.service.ScrapperService

class Tele2Parser(
    scrapperService: ScrapperService,
    name: String, baseUrl: String,
) : Parser(scrapperService, name, baseUrl) {
    override fun parse(): String {
        renewProxy()
        println("$logPrefix Page {$baseUrl} was parsed without any webdriver, using only proxy: $currentProxyAddress:$currentProxyPort}")
        return "$name parsed data"
    }
}
