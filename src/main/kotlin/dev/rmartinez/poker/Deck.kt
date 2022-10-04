package dev.rmartinez.poker

import dev.rmartinez.poker.Constants.CARD_NUMBERS
import dev.rmartinez.poker.Constants.CARD_WEIGHTS
import kotlin.collections.ArrayDeque

class Deck {
    private val cards: ArrayDeque<Card> = ArrayDeque()

    init {
        CardSuit.values().forEach { suit ->
            CARD_NUMBERS.forEach {
                val card = Card(it, suit, CARD_WEIGHTS[it]!!)

                cards.addFirst(card)
            }
        }
    }

    fun shuffle() {
        cards.shuffle()
    }

    fun draw(): Card {
        return cards.removeFirst()
    }

    override fun toString(): String {
       return cards.toString()
    }
}