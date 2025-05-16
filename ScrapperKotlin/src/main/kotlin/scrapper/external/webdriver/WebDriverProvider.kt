package org.example.scrapper.external.webdriver

import org.example.scrapper.external.webdriver.impl.ChromeWebDriver
import org.example.scrapper.external.webdriver.impl.FirefoxWebDriver
import org.example.scrapper.external.webdriver.impl.IExplorerWebDriver

// Фасад
object WebDriverProvider {
    fun getWebdriver(type: WebDriverType, version: String?) : WebDriver {
        return getWebdriver(type, version, null, null)
    }

    fun getWebdriver(type: WebDriverType, version: String?, proxyAddress: String?, proxyPort: String?): WebDriver{
        val driver = when(type) {
            WebDriverType.CHROME -> ChromeWebDriver(version ?: "114")
            WebDriverType.FIREFOX -> FirefoxWebDriver(version ?: "87")
            WebDriverType.IEXPLORER -> IExplorerWebDriver(version ?: "7")
        }
        driver.setProxy(proxyAddress, proxyPort)
        return driver
    }
}