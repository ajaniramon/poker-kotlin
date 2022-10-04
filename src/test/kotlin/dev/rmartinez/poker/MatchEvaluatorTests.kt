package dev.rmartinez.poker

import org.junit.jupiter.api.Test
import kotlin.test.assertFalse

class MatchEvaluatorTests {
    @Test
    fun `pair`() {
        val p1 = Hand(
            mutableListOf(
                Card("6", CardSuit.CLUBS),
                Card("7", CardSuit.CLUBS),
                Card("7", CardSuit.SPADES),
                Card("8", CardSuit.CLUBS),
                Card("10", CardSuit.DIAMONDS)
            )
        )

        val p2 = Hand(
            mutableListOf(
                Card("2", CardSuit.SPADES),
                Card("2", CardSuit.CLUBS),
                Card("5", CardSuit.SPADES),
                Card("9", CardSuit.DIAMONDS),
                Card("K", CardSuit.SPADES)
            )
        )

        val matchEvaluator = MatchEvaluatorImpl()

        assertFalse { matchEvaluator.evaluate(p1,p2).winner == Winners.PLAYER_2_WINS }
    }
}