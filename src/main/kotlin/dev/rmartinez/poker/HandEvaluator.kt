package dev.rmartinez.poker

object Winners {
    const val PLAYER_1_WINS = 1
    const val PLAYER_2_WINS = 2
    const val DRAW = -1
}

data class MatchResult(
    val winner: Int,
    val firstHandEvaluation: HandEvaluation,
    val secondHandEvaluation: HandEvaluation
) {
    override fun toString(): String {
        return """
            ------------------------------------------------------------
            🃏 Match ended. 
            
            Winner=$winner 
            P1 Hand: ${firstHandEvaluation.hand.cards}
            P2 Hand: ${secondHandEvaluation.hand.cards}
            
            Winner combination: 
            ${
                when (winner) {
                    Winners.PLAYER_1_WINS -> {
                        firstHandEvaluation.combination
                    }
                    Winners.PLAYER_2_WINS -> {
                        secondHandEvaluation.combination
                    }
                    else -> {
                        "none"
                    }
                }
            }
            ------------------------------------------------------------
            """.trimIndent()
    }
}

interface HandEvaluator {
    fun evaluateHand(hand: Hand): HandEvaluation
}

class HandEvaluatorImpl : HandEvaluator {
    private val pokerCombinationEvaluator = PokerCombinationEvaluatorImpl()

    override fun evaluateHand(hand: Hand): HandEvaluation {
        return HandEvaluation(hand, pokerCombinationEvaluator.getCombination(hand))
    }
}

interface PokerCombinationEvaluator {
    fun getCombination(hand: Hand): PokerCombination
}

class PokerCombinationEvaluatorImpl : PokerCombinationEvaluator {
    override fun getCombination(hand: Hand): PokerCombination {
        return when {
            hand.isRoyalFlush() -> PokerCombination.ROYAL_FLUSH
            hand.isStraightFlush() -> PokerCombination.STRAIGHT_FLUSH
            hand.isPoker() -> PokerCombination.POKER
            hand.isFullHouse() -> PokerCombination.FULL_HOUSE
            hand.areCardsSameSuit() -> PokerCombination.COLOR
            hand.isStraight() -> PokerCombination.STRAIGHT
            hand.isTrio() -> PokerCombination.TRIO
            hand.isDoublePair() -> PokerCombination.DOUBLE_PAIR
            hand.isPair() -> PokerCombination.PAIR

            else -> PokerCombination.HIGH_CARD
        }
    }
}