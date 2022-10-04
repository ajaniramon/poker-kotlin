package dev.rmartinez.poker

import dev.rmartinez.poker.Constants.CARD_WEIGHTS

data class Card(
    val number: String,
    val suit: CardSuit,
    val weight: Int = CARD_WEIGHTS[number]!!
) {
    override fun toString(): String {
        return "$number of ${suit.toSymbol()}"
    }
}

fun CardSuit.toSymbol(): String =
    when(this) {
        CardSuit.CLUBS -> "♣️"
        CardSuit.HEARTS -> "♥️"
        CardSuit.DIAMONDS -> "♦️"
        CardSuit.SPADES -> "♠️"
    }


enum class CardSuit {
    SPADES, CLUBS, HEARTS, DIAMONDS
}