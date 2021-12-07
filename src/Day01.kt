fun main() {
    fun part1(input: List<String>): Int {
        return input.mapIndexed { index, s -> s.toInt() > input.getOrElse(index - 1, {s}).toInt() }.count { it }
    }

    fun part2(input: List<String>): Int {
        var counter = 0
        var firstWindow = input.filterIndexed{ index, _ ->  index in 0..2}.sumOf { it.toInt() }
        var secondWindow: Int
        for(i in 1 until input.size - 2)
        {
            secondWindow = input.filterIndexed{ index, _ ->  index in i..i+2}.sumOf { it.toInt() }

            if(firstWindow < secondWindow) {
                counter++

            }
            firstWindow = secondWindow
        }
        return counter
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01_test")
    check(part1(testInput) == 7)
    check(part2(testInput) == 5)

    val input = readInput("Day01")
    println(part1(input))
    println(part2(input))
}
