package dev.rmartinez.poker

data class HandEvaluation(val hand: Hand, val combination: PokerCombination)

data class Hand(
    val cards: MutableList<Card> = mutableListOf()
) {
    fun areCardsInConsecutiveOrder(): Boolean {
        cards.sortBy { it.weight }

        return isConsecutive(cards.map { it.weight }.toIntArray())
    }

    fun areCardsSameSuit(): Boolean {
        return allEqual(cards.map { it.suit.name })
    }

    fun isRoyalFlush(): Boolean = isStraightFlush() && areCardsSameSuit()

    fun isStraightFlush(): Boolean {
        cards.sortBy { it.weight }

        return cards.map { it.weight } == listOf(10, 11, 12, 13, 14)
    }

    fun isStraight(): Boolean = areCardsInConsecutiveOrder() || isAceAscendingStraight()

    fun isPoker(): Boolean {
        return cards.any { cardOcurencesByNumber(it) == 4 }
    }

    fun isFullHouse(): Boolean {
        return cards.any { cardOcurencesByNumber(it) == 3 } && cards.any { cardOcurencesByNumber(it) == 2 }
    }

    fun isTrio(): Boolean {
        return cards.any { cardOcurencesByNumber(it) == 3 }
    }

    fun isDoublePair(): Boolean {
        return cards.filter { cardOcurencesByNumber(it) == 2 }.size == 4
    }

    fun isPair(): Boolean {
        return cards.any { cardOcurencesByNumber(it) == 2 }
    }

    fun getTotalWeight(): Int = cards.sumOf { it.weight }

    fun getHighestCardWeight(): Int = cards.maxOf { it.weight }

    fun getHighestPairWeight(): Int = cards.filter { cardOcurencesByNumber(it) == 2 }.maxOf { it.weight }
    fun getLowestPairWeight(): Int = cards.filter { cardOcurencesByNumber(it) == 2 }.minOf { it.weight }

    fun getCardsWeightByOcurrences(occurences: Int):  Int {
        return cards.filter { cardOcurencesByNumber(it) == occurences }.sumOf { it.weight }
    }

    private fun cardOcurencesByNumber(card: Card): Int {
        return cards.filter { it.number == card.number }.size
    }

    private fun isAceAscendingStraight() = cards.map { it.weight } == listOf(2, 3, 4, 5, 14)
}