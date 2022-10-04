package dev.rmartinez.poker

fun compareWeights(firstWeight: Int, secondWeight: Int): Int {
    return when {
        firstWeight > secondWeight -> Winners.PLAYER_1_WINS
        secondWeight > firstWeight -> Winners.PLAYER_2_WINS
        else -> Winners.DRAW
    }
}