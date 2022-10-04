package dev.rmartinez.poker

fun main(args: Array<String>) {
  val elapsed = measureTimeMillis {
      repeat(1000000) {
          match()
      }
  }
    println("Simulation ended. Elapsed: $elapsed ms.")
}

fun match() {
    val deck = Deck()

    val player1 = Player("ajaniramon")
    val player2 = Player("danielobitz")

    val match = Match(deck, player1, player2)

    println(match.play())
}

private fun measureTimeMillis(body: () -> Unit): Long {
    val start = System.currentTimeMillis()

    body()

    return System.currentTimeMillis() - start
}
