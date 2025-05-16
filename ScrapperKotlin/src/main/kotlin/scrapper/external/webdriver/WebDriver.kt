package org.example.scrapper.external.webdriver


abstract class WebDriver internal constructor(
    private val name: String,
    private val version: String,
    private var proxyAddress: String? = null,
    private var proxyPort: String? = null
) {
    internal var currentUrl: String? = null
    internal val currentCookies = mutableMapOf<String, String>()

    // Прокси может быть, а может и нет. Он не обязателен, и ставится отдельно от создания вебдрайвера
    fun setProxy(proxyAddress: String?, proxyPort: String?) {
        this.proxyAddress = proxyAddress
        this.proxyPort = proxyPort
    }

    private fun getProxySignature(): String {
        var result: String? = null
        if (proxyAddress != null) {
            result = proxyAddress
            if (proxyPort != null) {
                result += ":$proxyPort"
            }
        }
        return result ?: "no proxy"
    }

    fun getSignature(): String {
        val proxyConfiguration = getProxySignature()
        return "$name v. $version ($proxyConfiguration)"
    }

    fun getUrlCookies(): String?{
        return currentCookies[currentUrl]
    }

    abstract fun getCurrentPage(): String
    abstract fun navigateTo(url: String)

}