package org.example

import org.example.scrapper.service.ScrapperService
import org.example.ui.ConsoleUIService

fun main() {
    ConsoleUIService(ScrapperService()).start()
}