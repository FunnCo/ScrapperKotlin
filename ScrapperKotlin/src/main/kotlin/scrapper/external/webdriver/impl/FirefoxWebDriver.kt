package org.example.scrapper.external.webdriver.impl

import org.example.scrapper.external.webdriver.WebDriver
import java.util.concurrent.TimeUnit

class FirefoxWebDriver(version: String) : WebDriver("firefox", version) {
    override fun getCurrentPage(): String {
        return "Page of $currentUrl opened by Coolest firefox"
    }

    // Имитация открытия страницы
    override fun navigateTo(url: String) {
        currentUrl = url
        TimeUnit.SECONDS.sleep(1)
        currentCookies[url] = "firefox_cookie_name:firefox_cookie_payload"
    }
}