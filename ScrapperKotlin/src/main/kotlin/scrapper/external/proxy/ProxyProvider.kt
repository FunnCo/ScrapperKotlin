package org.example.scrapper.external.proxy

object ProxyProvider {
    val proxies = listOf("192.168.1.45:23435", "10.0.34.21:23431", "172.16.254.12:413", "198.51.100.3:52345", "91.198.174.192:10231")

    fun getRandomProxy(): Pair<String, String>{
        val randomProxy = proxies.random()
        return Pair(randomProxy.split(":")[0], randomProxy.split(":")[1])
    }
}