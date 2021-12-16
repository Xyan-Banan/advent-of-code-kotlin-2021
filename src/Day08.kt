fun main() {
    fun List<String>.parseInputOutput() = map { it.split(" | ").map { it.split(" ") } }.map { it.first() to it.last() }

    fun part1(input: List<Pair<List<String>, List<String>>>): Int {
        val outputs = input.flatMap { it.second }
        return outputs.count { it.length in listOf(2, 3, 4, 7) }
    }

    fun decodeInput(input: List<String>): List<Set<Char>> {
        val one = input.first { it.length == 2 }
        val seven = input.first { it.length == 3 }

        val eight = input.first { it.length == 7 }
        val six = input.filter { it.length == 6 }.first { !it.toList().containsAll(one.toList()) }
        val rightTop = eight.first { it !in six }
        val rightBot = one.first { it != rightTop }

        val three = input.filter { it.length == 5 }.first { it.toList().containsAll(listOf(rightTop, rightBot)) }
        val nine = input.filter { it.length == 6 }.first { it.toList().containsAll(three.toList()) }
        val leftTop = nine.first { it !in three }
        val leftBot = eight.first { it !in nine }
        val zero = input.filter { it.length == 6 }.first { it.toList().containsAll(listOf(leftTop, leftBot, rightTop)) }
        val two = input.filter { it.length == 5 }.first { it.toList().containsAll(listOf(leftBot, rightTop)) }
        val five = six.toSet().intersect(nine.toSet()).joinToString("")
        val four = input.first { it.length == 4 }

        return listOf(zero, one, two, three, four, five, six, seven, eight, nine).map { it.toSet() }
    }

    fun decodeOutput(outputs: List<String>, decodedInput: List<Set<Char>>) =
        outputs.map { decodedInput.indexOf(it.toSet()) }.joinToString("").toInt()

    fun part2(input: List<Pair<List<String>, List<String>>>) =
        input.sumOf { (inputs, outputs) -> decodeOutput(outputs, decodeInput(inputs)) }


//  test if implementation meets criteria from the description, like:
    val testInput = readInput("Day08_test").parseInputOutput()
    check(part1(testInput) == 26)
    check(part2(testInput) == 61229)

    val input = readInput("Day08").parseInputOutput()
    println(part1(input))
    println(part2(input))
}
