package dev.rmartinez.poker

import com.fasterxml.jackson.core.util.DefaultPrettyPrinter
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import java.io.File
import java.util.*
import kotlin.system.exitProcess

val debug = System.getenv("DEBUG") == "true"

fun main() {
    menu()

    try {
        val choice = readln().toInt()

        doChoice(choice)
    } catch (e: Exception) {
        println("bad choice")
        exitProcess(1)
    }
}

fun menu() {
    println("--- POKER by heathcliff ---")

    println("Choose option:")
    println("1. Interactive match")
    println("2. n matches simulation")
    println("0. Exit")
}

object Choices {
    const val INTERACTIVE_MATCH = 1
    const val MATCH_SIMULTATION = 2
    const val EXIT = 0
}

fun doChoice(choice: Int) {
    when (choice) {
        Choices.INTERACTIVE_MATCH -> interactiveMatch()
        Choices.MATCH_SIMULTATION -> matchSimulation()
        Choices.EXIT -> exitProcess(0)
    }
}

fun matchSimulation() {
    println("Enter number of matches (default 1):")

    val numberOfMatches = try {
        readln().toInt()
    } catch (e: Exception) {
        println("bad choice")
        exitProcess(1)
    }

    println("Starting simulation of $numberOfMatches...")

    val results = mutableListOf<MatchResult>()
    val elapsed = measureTimeMillis {
        repeat(numberOfMatches) {
            results.add(match())
        }
    }

    println("Simulation finished. Elapsed: $elapsed ms")
    println("Dumping results...")
    jacksonObjectMapper().writer(DefaultPrettyPrinter()).writeValue(File("simulation-${UUID.randomUUID()}.json"), results)
    println("DONE")
}

fun interactiveMatch() {
    println("this is a shit and i don't know when im gonna develop this feature")
}

fun match(): MatchResult {
    val deck = Deck()

    val player1 = Player("ajaniramon")
    val player2 = Player("danielobitz")

    val result = Match(deck, player1, player2).play()

    if (debug) {
        println(result)
    }

    return result
}

private fun measureTimeMillis(body: () -> Unit): Long {
    val start = System.currentTimeMillis()

    body()

    return System.currentTimeMillis() - start
}