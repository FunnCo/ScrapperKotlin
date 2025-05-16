package org.example.scrapper.parser

import org.example.scrapper.service.ScrapperService
import java.util.concurrent.TimeUnit

abstract class Parser(val scrapperService: ScrapperService, val name: String, val baseUrl: String) {
    abstract fun parse(): String
    var currentProxyAddress = "127.0.0.1"
    var currentProxyPort = "8080"
    val logPrefix = "Parser $name log:"

    fun renewProxy() {
        val newProxy = scrapperService.getNewProxy()
        currentProxyAddress = newProxy.first
        currentProxyPort = newProxy.second
    }

    fun resolveCaptcha(page: String){
        while (!scrapperService.handleCaptcha(page)){
            println("$logPrefix failed to solve captcha, trying again")
        }
        println("$logPrefix solved captcha")
    }

    fun imitateWork(){
        TimeUnit.SECONDS.sleep(1)
    }
}