package org.example.scrapper.external.webdriver.impl

import org.example.scrapper.external.webdriver.WebDriver
import java.util.concurrent.TimeUnit

class IExplorerWebDriver(version: String): WebDriver("iexplorer", version) {
    override fun getCurrentPage(): String {
        return "Page of $currentUrl opened by Slowest Internet Explorer"
    }

    // Имитация открытия страницы
    override fun navigateTo(url: String) {
        currentUrl = url
        TimeUnit.SECONDS.sleep(1)
        currentCookies[url] = "ie_cookie_name:ie_cookie_payload"
    }
}