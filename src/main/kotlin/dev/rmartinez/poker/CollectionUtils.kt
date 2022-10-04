package dev.rmartinez.poker


fun<T> allEqual(numbers : List<T>): Boolean = numbers.all { it == numbers[0] }

fun isConsecutive(array: IntArray): Boolean {
    if (array.size <= 1) {
        return true
    }

    var min = Int.MAX_VALUE
    var max = Int.MIN_VALUE

    for (i in array) {
        if (i < min) {
            min = i
        }
        if (i > max) {
            max = i
        }
    }

    if (max - min != array.size - 1) {
        return false
    }

    val found: MutableSet<Int> = HashSet()

    for (i in array) {
        if (found.contains(i)) {
            return false
        }

        found.add(i)
    }

    return true
}