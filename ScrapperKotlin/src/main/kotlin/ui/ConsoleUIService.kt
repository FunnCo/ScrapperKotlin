package org.example.ui

import org.example.scrapper.service.ScrapperService

class ConsoleUIService(private val scrapperService: ScrapperService) {
    private var shouldExit = false

    fun start() {
        println("Welcome to the website parser!")

        while (!shouldExit) {
            printMenu()
            when (readlnOrNull()?.trim()) {
                "1" -> handlePredefinedSites()
                "2" -> handleCustomSite()
                "3" -> exit()
                else -> showInvalidInputMessage()
            }
        }
    }

    private fun printMenu() {
        println("\n=== Main Menu ===")
        println("1. Parse all predefined sites")
        println("2. Parse a custom site")
        println("3. Exit")
        print("Please select an option: ")
    }

    private fun handlePredefinedSites() {
        println("\nStarting parsing of predefined sites...")
        try {
            val result = scrapperService.parseAllPredefined()
            println("\nParsing results:")
            println(result)
        } catch (e: Exception) {
            println("Error during parsing: ${e.message}")
        }
    }

    private fun handleCustomSite() {
        println("\nEnter custom website data:")
        print("Website URL: ")
        val url = readlnOrNull()?.trim() ?: ""
        print("Website name: ")
        val name = readlnOrNull()?.trim() ?: ""

        if (url.isEmpty() || name.isEmpty()) {
            println("Error: URL and name cannot be empty!")
            return
        }

        println("\nStarting parsing of '$name'...")
        try {
            val result = scrapperService.parseCustom(url, name)
            println("\nParsing results:")
            println(result)
        } catch (e: Exception) {
            println("Error during parsing: ${e.message}")
        }
    }

    private fun exit() {
        shouldExit = true
        println("\nShutting down...")
    }

    private fun showInvalidInputMessage() {
        println("Invalid input! Please select an option from the list.")
    }
}