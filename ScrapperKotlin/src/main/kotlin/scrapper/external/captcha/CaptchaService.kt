package org.example.scrapper.external.captcha

import java.util.concurrent.TimeUnit
import kotlin.random.Random

object CaptchaService {

    fun resolveCaptcha(page: String): Boolean{
        TimeUnit.SECONDS.sleep(2) // Имитация работы сервиса капчи
        return Random.nextInt(1, 11) < 8
    }

}