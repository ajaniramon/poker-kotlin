package dev.rmartinez.poker

class Match(private val deck: Deck,
            private val player1: Player,
            private val player2: Player,
            ) {

    private val matchEvaluator = MatchEvaluatorImpl()

    init {
        deck.shuffle()

        player1.drawHand(deck)
        player2.drawHand(deck)
    }

    fun play(): MatchResult {
        return matchEvaluator.evaluate(player1.getHand(), player2.getHand())
    }
}