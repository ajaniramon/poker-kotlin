package dev.rmartinez.poker

import org.junit.jupiter.api.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class PokerTests {

    @Test
    fun `check if cards in hand are consecutive`() {
        val consecutiveHand = Hand(
            mutableListOf(
                Card("J", CardSuit.CLUBS),
                Card("10", CardSuit.DIAMONDS),
                Card("A", CardSuit.HEARTS),
                Card("Q", CardSuit.SPADES),
                Card("K", CardSuit.DIAMONDS)
            )
        )

        assertTrue { consecutiveHand.areCardsInConsecutiveOrder() }

        val nonConsecutiveHand = Hand(
            mutableListOf(
                Card("4", CardSuit.CLUBS),
                Card("10", CardSuit.DIAMONDS),
                Card("A", CardSuit.HEARTS),
                Card("Q", CardSuit.SPADES),
                Card("K", CardSuit.DIAMONDS)
            )
        )

        assertFalse { nonConsecutiveHand.areCardsInConsecutiveOrder() }
    }

    @Test
    fun `check if cards are all same suit`() {
        val sameSuitHand = Hand(
            mutableListOf(
                Card("J", CardSuit.CLUBS),
                Card("10", CardSuit.CLUBS),
                Card("A", CardSuit.CLUBS),
                Card("Q", CardSuit.CLUBS),
                Card("K", CardSuit.CLUBS)
            )
        )

        assertTrue { sameSuitHand.areCardsSameSuit() }

        val nonSameSuitHand = Hand(
            mutableListOf(
                Card("J", CardSuit.CLUBS),
                Card("10", CardSuit.DIAMONDS),
                Card("A", CardSuit.CLUBS),
                Card("Q", CardSuit.CLUBS),
                Card("K", CardSuit.CLUBS)
            )
        )

        assertFalse { nonSameSuitHand.areCardsSameSuit() }
    }

    @Test
    fun `check royal flush`() {
        val royalFlushHand = Hand(
            mutableListOf(
                Card("J", CardSuit.CLUBS),
                Card("10", CardSuit.CLUBS),
                Card("Q", CardSuit.CLUBS),
                Card("A", CardSuit.CLUBS),
                Card("K", CardSuit.CLUBS)
            )
        )

        assertTrue { royalFlushHand.isRoyalFlush() }

        val nonRoyalFlushHand = Hand(
            mutableListOf(
                Card("J", CardSuit.CLUBS),
                Card("10", CardSuit.CLUBS),
                Card("3", CardSuit.CLUBS),
                Card("7", CardSuit.CLUBS),
                Card("5", CardSuit.CLUBS)
            )
        )

        assertFalse { nonRoyalFlushHand.isRoyalFlush() }
    }

    @Test
    fun `check straight flush`() {
        val straightFlushHand = Hand(
            mutableListOf(
                Card("J", CardSuit.CLUBS),
                Card("10", CardSuit.DIAMONDS),
                Card("Q", CardSuit.CLUBS),
                Card("A", CardSuit.SPADES),
                Card("K", CardSuit.CLUBS)
            )
        )

        assertTrue { straightFlushHand.isStraightFlush() }


        val nonStraightFlushHand = Hand(
            mutableListOf(
                Card("J", CardSuit.CLUBS),
                Card("K", CardSuit.DIAMONDS),
                Card("Q", CardSuit.CLUBS),
                Card("A", CardSuit.SPADES),
                Card("K", CardSuit.CLUBS)
            )
        )

        assertFalse { nonStraightFlushHand.isStraightFlush() }
    }

    @Test
    fun `check straight`() {
        val aceAscendingStraightHand = Hand(
            mutableListOf(
                Card("3", CardSuit.CLUBS),
                Card("2", CardSuit.DIAMONDS),
                Card("A", CardSuit.HEARTS),
                Card("4", CardSuit.SPADES),
                Card("5", CardSuit.CLUBS)
            )
        )

        assertTrue { aceAscendingStraightHand.isStraight() }

        val straightHand = Hand(
            mutableListOf(
                Card("A", CardSuit.CLUBS),
                Card("5", CardSuit.DIAMONDS),
                Card("4", CardSuit.HEARTS),
                Card("3", CardSuit.SPADES),
                Card("2", CardSuit.CLUBS)
            )
        )

        assertTrue { straightHand.isStraight() }

        val nonStraightHand = Hand(
            mutableListOf(
                Card("J", CardSuit.CLUBS),
                Card("9", CardSuit.DIAMONDS),
                Card("J", CardSuit.HEARTS),
                Card("J", CardSuit.SPADES),
                Card("K", CardSuit.CLUBS)
            )
        )

        assertFalse { nonStraightHand.isStraight() }
    }

    @Test
    fun `check poker`() {
        val pokerHand = Hand(
            mutableListOf(
                Card("J", CardSuit.CLUBS),
                Card("J", CardSuit.DIAMONDS),
                Card("J", CardSuit.HEARTS),
                Card("J", CardSuit.SPADES),
                Card("K", CardSuit.CLUBS)
            )
        )

        assertTrue { pokerHand.isPoker() }

        val nonPokerHand = Hand(
            mutableListOf(
                Card("J", CardSuit.CLUBS),
                Card("9", CardSuit.DIAMONDS),
                Card("J", CardSuit.HEARTS),
                Card("J", CardSuit.SPADES),
                Card("K", CardSuit.CLUBS)
            )
        )

        assertFalse { nonPokerHand.isPoker() }
    }

    @Test
    fun `check full house`() {
        val fullHouseHand = Hand(
            mutableListOf(
                Card("J", CardSuit.CLUBS),
                Card("J", CardSuit.DIAMONDS),
                Card("J", CardSuit.HEARTS),
                Card("Q", CardSuit.SPADES),
                Card("Q", CardSuit.CLUBS)
            )
        )

        assertTrue { fullHouseHand.isFullHouse() }

        val nonFullHouseHand = Hand(
            mutableListOf(
                Card("J", CardSuit.CLUBS),
                Card("J", CardSuit.DIAMONDS),
                Card("J", CardSuit.HEARTS),
                Card("Q", CardSuit.SPADES),
                Card("K", CardSuit.CLUBS)
            )
        )

        assertFalse { nonFullHouseHand.isFullHouse() }
    }

    @Test
    fun `check trio`() {
        val trioHand = Hand(
            mutableListOf(
                Card("J", CardSuit.CLUBS),
                Card("J", CardSuit.DIAMONDS),
                Card("J", CardSuit.HEARTS),
                Card("9", CardSuit.SPADES),
                Card("Q", CardSuit.CLUBS)
            )
        )

        assertTrue { trioHand.isTrio() }

        val nonTrioHand = Hand(
            mutableListOf(
                Card("J", CardSuit.CLUBS),
                Card("J", CardSuit.DIAMONDS),
                Card("8", CardSuit.HEARTS),
                Card("9", CardSuit.SPADES),
                Card("Q", CardSuit.CLUBS)
            )
        )

        assertFalse { nonTrioHand.isTrio() }
    }

    @Test
    fun `check double pair`() {
        val doublePairHand = Hand(
            mutableListOf(
                Card("J", CardSuit.CLUBS),
                Card("J", CardSuit.DIAMONDS),
                Card("Q", CardSuit.HEARTS),
                Card("9", CardSuit.SPADES),
                Card("Q", CardSuit.CLUBS)
            )
        )

        assertTrue { doublePairHand.isDoublePair() }

        val nonDoublePairHand = Hand(
            mutableListOf(
                Card("J", CardSuit.CLUBS),
                Card("J", CardSuit.DIAMONDS),
                Card("2", CardSuit.HEARTS),
                Card("9", CardSuit.SPADES),
                Card("Q", CardSuit.CLUBS)
            )
        )

        assertFalse { nonDoublePairHand.isDoublePair() }
    }

    @Test
    fun `check pair`() {
        val pairHand = Hand(
            mutableListOf(
                Card("J", CardSuit.CLUBS),
                Card("J", CardSuit.DIAMONDS),
                Card("8", CardSuit.HEARTS),
                Card("9", CardSuit.SPADES),
                Card("Q", CardSuit.CLUBS)
            )
        )

        assertTrue { pairHand.isPair() }

        val nonPairHand = Hand(
            mutableListOf(
                Card("J", CardSuit.CLUBS),
                Card("7", CardSuit.DIAMONDS),
                Card("2", CardSuit.HEARTS),
                Card("9", CardSuit.SPADES),
                Card("Q", CardSuit.CLUBS)
            )
        )

        assertFalse { nonPairHand.isPair() }
    }
}