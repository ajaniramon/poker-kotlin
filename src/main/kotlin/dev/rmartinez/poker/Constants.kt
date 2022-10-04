package dev.rmartinez.poker

enum class PokerCombination {
    ROYAL_FLUSH,
    STRAIGHT_FLUSH,
    POKER,
    FULL_HOUSE,
    COLOR,
    STRAIGHT,
    TRIO,
    DOUBLE_PAIR,
    PAIR,
    HIGH_CARD
}
object Constants {
    val COMBINATION_WEIGHTS = mapOf(
         PokerCombination.HIGH_CARD to 10,
         PokerCombination.PAIR to 20,
         PokerCombination.DOUBLE_PAIR to 30,
         PokerCombination.TRIO to 40,
         PokerCombination.STRAIGHT to 50,
         PokerCombination.COLOR to 60,
         PokerCombination.FULL_HOUSE to 70,
         PokerCombination.POKER to 80,
         PokerCombination.STRAIGHT_FLUSH to 90,
         PokerCombination.ROYAL_FLUSH to 100
    )

    const val MAX_HAND_SIZE = 5

    val CARD_NUMBERS = arrayOf("A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K")

    val CARD_WEIGHTS = mapOf(
        "2" to 2,
        "3" to 3,
        "4" to 4,
        "5" to 5,
        "6" to 6,
        "7" to 7,
        "8" to 8,
        "9" to 9,
        "10" to 10,
        "J" to 11,
        "Q" to 12,
        "K" to 13,
        "A" to 14
    )
}


