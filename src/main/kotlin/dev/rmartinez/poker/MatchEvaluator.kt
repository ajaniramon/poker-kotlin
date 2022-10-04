package dev.rmartinez.poker

import dev.rmartinez.poker.Constants.COMBINATION_WEIGHTS

interface MatchEvaluator {
    fun evaluate(firstHand: Hand, secondHand: Hand): MatchResult
}

class MatchEvaluatorImpl: MatchEvaluator {
    private val handEvaluator = HandEvaluatorImpl()
    private val matchUntier = MatchUntierImpl()

    override fun evaluate(firstHand: Hand, secondHand: Hand): MatchResult {
        val firstHandEvaluation = handEvaluator.evaluateHand(firstHand)
        val secondHandEvaluation = handEvaluator.evaluateHand(secondHand)

        val winner = getWinnerByCombination(firstHandEvaluation, secondHandEvaluation)

        return if(winner != Winners.DRAW) {
            MatchResult(winner, firstHandEvaluation, secondHandEvaluation)
        } else {
            return matchUntier.untie(firstHandEvaluation, secondHandEvaluation)
        }
    }

    private fun getWinnerByCombination(firstHandEvaluation: HandEvaluation, secondHandEvaluation: HandEvaluation): Int {
        val firstHandCombinationWeight = COMBINATION_WEIGHTS[firstHandEvaluation.combination]!!
        val secondHandCombinationWeight = COMBINATION_WEIGHTS[secondHandEvaluation.combination]!!

        return compareWeights(firstHandCombinationWeight, secondHandCombinationWeight)
    }
}