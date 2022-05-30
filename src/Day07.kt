import kotlin.math.abs
import kotlin.math.roundToInt

fun main() {

    fun part1(input: List<Int>): Int {
        val med = input.sorted()[input.size / 2]
        return input.sumOf { abs(it - med) }
    }

    fun part2(input: List<Int>): Int {
        val avg = input.average().minus(0.1).roundToInt()
        return input.map { abs(it - avg) }.sumOf { n -> n * (n + 1) / 2 }
    }


//  test if implementation meets criteria from the description, like:
    val testInput = readIntList("Day07_test", ",")
    check(part1(testInput) == 37)
    check(part2(testInput) == 168)

    val input = readIntList("Day07", ",")
    println(part1(input))
    println(part2(input))
}
