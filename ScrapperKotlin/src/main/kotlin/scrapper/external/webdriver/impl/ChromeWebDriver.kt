package org.example.scrapper.external.webdriver.impl

import org.example.scrapper.external.webdriver.WebDriver
import java.util.concurrent.TimeUnit

class ChromeWebDriver(version: String) : WebDriver("chrome", version) {
    override fun getCurrentPage(): String {
        return "Page of $currentUrl opened by Almighty Chrome"
    }

    // Имитация открытия страницы
    override fun navigateTo(url: String) {
        currentUrl = url
        TimeUnit.SECONDS.sleep(1)
        currentCookies[url] = "chrome_cookie_name:chrome_cookie_payload"
    }
}