package dev.rmartinez.poker

fun Hand.cardOcurencesByNumber(card: Card): Int {
    return cards.filter { it.number == card.number }.size
}

fun Hand.isAceAscendingStraight() = cards.map { it.weight } == listOf(2, 3, 4, 5, 14)
fun Hand.areCardsInConsecutiveOrder(): Boolean {
    cards.sortBy { it.weight }

    return isConsecutive(cards.map { it.weight }.toIntArray())
}

fun Hand.areCardsSameSuit(): Boolean {
    return allEqual(cards.map { it.suit.name })
}

fun Hand.isRoyalFlush(): Boolean = isStraightFlush() && areCardsSameSuit()

fun Hand.isStraightFlush(): Boolean {
    cards.sortBy { it.weight }

    return cards.map { it.weight } == listOf(10, 11, 12, 13, 14)
}

fun Hand.isStraight(): Boolean = areCardsInConsecutiveOrder() || isAceAscendingStraight()

fun Hand.isPoker(): Boolean {
    return cards.any { cardOcurencesByNumber(it) == 4 }
}

fun Hand.isFullHouse(): Boolean {
    return cards.any { cardOcurencesByNumber(it) == 3 } && cards.any { cardOcurencesByNumber(it) == 2 }
}

fun Hand.isTrio(): Boolean {
    return cards.any { cardOcurencesByNumber(it) == 3 }
}

fun Hand.isDoublePair(): Boolean {
    return cards.filter { cardOcurencesByNumber(it) == 2 }.size == 4
}

fun Hand.isPair(): Boolean {
    return cards.any { cardOcurencesByNumber(it) == 2 }
}

fun Hand.getTotalWeight(): Int = cards.sumOf { it.weight }

fun Hand.getHighestCardWeight(): Int = cards.maxOf { it.weight }

fun Hand.getHighestPairWeight(): Int = cards.filter { cardOcurencesByNumber(it) == 2 }.maxOf { it.weight }
fun Hand.getLowestPairWeight(): Int = cards.filter { cardOcurencesByNumber(it) == 2 }.minOf { it.weight }

fun Hand.getCardsWeightByOcurrences(occurences: Int): Int {
    return cards.filter { cardOcurencesByNumber(it) == occurences }.sumOf { it.weight }
}