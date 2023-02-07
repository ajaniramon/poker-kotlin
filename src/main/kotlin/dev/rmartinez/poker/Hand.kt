package dev.rmartinez.poker

data class HandEvaluation(val hand: Hand, val combination: PokerCombination)

data class Hand(
    val cards: MutableList<Card> = mutableListOf()
)

