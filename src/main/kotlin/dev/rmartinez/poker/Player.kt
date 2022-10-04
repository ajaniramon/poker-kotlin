package dev.rmartinez.poker

import dev.rmartinez.poker.Constants.MAX_HAND_SIZE


class Player(val name: String) {
    private val hand = Hand()

    fun getHand() = hand

    fun drawHand(deck: Deck) {
        hand.cards.clear()
        (hand.cards.size until MAX_HAND_SIZE).forEach { draw(deck) }
    }

    fun draw(deck: Deck) {
        val drawnCard = deck.draw()

        hand.cards.add(drawnCard)
    }
}