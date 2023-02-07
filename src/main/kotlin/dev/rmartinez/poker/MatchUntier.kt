package dev.rmartinez.poker

interface MatchUntier {
    fun untie(firstHandEvaluation: HandEvaluation, secondHandEvaluation: HandEvaluation): MatchResult
}

class MatchUntierImpl : MatchUntier {
    override fun untie(firstHandEvaluation: HandEvaluation, secondHandEvaluation: HandEvaluation): MatchResult {
        return when (firstHandEvaluation.combination) {
            PokerCombination.ROYAL_FLUSH, PokerCombination.STRAIGHT_FLUSH -> MatchResult(
                Winners.DRAW,
                firstHandEvaluation,
                secondHandEvaluation
            )

            PokerCombination.POKER -> untiePoker(firstHandEvaluation, secondHandEvaluation)
            PokerCombination.FULL_HOUSE -> untieFullHouse(firstHandEvaluation, secondHandEvaluation)
            PokerCombination.STRAIGHT -> untieByTotalWeight(firstHandEvaluation, secondHandEvaluation)
            PokerCombination.TRIO -> untieTrio(firstHandEvaluation, secondHandEvaluation)
            PokerCombination.DOUBLE_PAIR -> untieDoublePair(firstHandEvaluation, secondHandEvaluation)
            PokerCombination.PAIR -> untiePair(firstHandEvaluation, secondHandEvaluation)

            PokerCombination.COLOR, PokerCombination.HIGH_CARD -> untieByHighestCard(
                firstHandEvaluation,
                secondHandEvaluation
            )
        }
    }

    private fun untieFullHouse(firstHandEvaluation: HandEvaluation, secondHandEvaluation: HandEvaluation): MatchResult {
        val untieByTrio = untieByCombinationWeight(3, firstHandEvaluation, secondHandEvaluation)

        return if (untieByTrio.winner != Winners.DRAW) {
            untieByTrio
        } else {
            untieByCombinationWeight(2, firstHandEvaluation, secondHandEvaluation)
        }
    }

    private fun untiePoker(firstHandEvaluation: HandEvaluation, secondHandEvaluation: HandEvaluation): MatchResult {
        val untieByPoker = untieByCombinationWeight(4, firstHandEvaluation, secondHandEvaluation)

        return if (untieByPoker.winner != Winners.DRAW) {
            untieByPoker
        } else {
            untieByHighestCard(firstHandEvaluation, secondHandEvaluation)
        }
    }

    private fun untieTrio(firstHandEvaluation: HandEvaluation, secondHandEvaluation: HandEvaluation): MatchResult {
        val untieByTio = untieByCombinationWeight(3, firstHandEvaluation, secondHandEvaluation)

        return if (untieByTio.winner != Winners.DRAW) {
            untieByTio
        } else {
            untieByHighestCard(firstHandEvaluation, secondHandEvaluation)
        }
    }

    private fun untieDoublePair(
        firstHandEvaluation: HandEvaluation,
        secondHandEvaluation: HandEvaluation
    ): MatchResult {
        val firstHandWeight = firstHandEvaluation.hand.getHighestPairWeight()
        val secondHandWeight = secondHandEvaluation.hand.getHighestPairWeight()

        val winner = compareWeights(firstHandWeight, secondHandWeight)

        return if (winner != Winners.DRAW) {
            MatchResult(winner, firstHandEvaluation, secondHandEvaluation)
        } else {
            val firstLowestPairWeight = firstHandEvaluation.hand.getLowestPairWeight()
            val secondLowestPairWeight = secondHandEvaluation.hand.getLowestPairWeight()

            MatchResult(
                compareWeights(firstLowestPairWeight, secondLowestPairWeight),
                firstHandEvaluation,
                secondHandEvaluation
            )
        }
    }

    private fun untiePair(firstHandEvaluation: HandEvaluation, secondHandEvaluation: HandEvaluation): MatchResult {
        val untieByPair = untieByCombinationWeight(2, firstHandEvaluation, secondHandEvaluation)

        return if (untieByPair.winner != Winners.DRAW) {
            untieByPair
        } else {
            untieByHighestCard(firstHandEvaluation, secondHandEvaluation)
        }

    }

    private fun untieByHighestCard(
        firstHandEvaluation: HandEvaluation,
        secondHandEvaluation: HandEvaluation
    ): MatchResult {
        val firstHandWeight = firstHandEvaluation.hand.getHighestCardWeight()
        val secondHandWeight = secondHandEvaluation.hand.getHighestCardWeight()

        val winner = compareWeights(firstHandWeight, secondHandWeight)

        return MatchResult(winner, firstHandEvaluation, secondHandEvaluation)
    }

    private fun untieByTotalWeight(
        firstHandEvaluation: HandEvaluation,
        secondHandEvaluation: HandEvaluation
    ): MatchResult {
        val firstHandWeight = firstHandEvaluation.hand.getTotalWeight()
        val secondHandWeight = secondHandEvaluation.hand.getTotalWeight()

        val winner = compareWeights(firstHandWeight, secondHandWeight)

        return MatchResult(winner, firstHandEvaluation, secondHandEvaluation)
    }

    private fun untieByCombinationWeight(
        weight: Int,
        firstHandEvaluation: HandEvaluation,
        secondHandEvaluation: HandEvaluation
    ): MatchResult {
        val firstHandWeight = firstHandEvaluation.hand.getCardsWeightByOcurrences(weight)
        val secondHandWeight = secondHandEvaluation.hand.getCardsWeightByOcurrences(weight)

        val winner = compareWeights(firstHandWeight, secondHandWeight)

        return MatchResult(winner, firstHandEvaluation, secondHandEvaluation)
    }
}