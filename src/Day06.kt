fun main() {

    fun part1(input: List<Int>): Int {
        val inputCopy = input.toMutableList()
        repeat(80) {
            for (fishInd in inputCopy.indices) {
                inputCopy[fishInd]--
                if (inputCopy[fishInd] < 0) {
                    inputCopy[fishInd] = 6
                    inputCopy.add(8)
                }
            }
        }
        return inputCopy.size
    }

    fun part2(input: List<Int>): Long {
        var map = input.groupingBy { it }.eachCount().mapValues { it.value.toLong() }.toMutableMap().apply { for (i in 0..8) putIfAbsent(i, 0L) }
        val nextDayMap = ((0..8) zip Array(9) { 0L }).toMap().toMutableMap()
        var numberOfFish = input.size.toLong()
        repeat(256) {
            for (key in (8 downTo 0)) {
                val value = map.getValue(key)
                if (key > 0)
                    nextDayMap[key - 1] = value
                else {
                    nextDayMap[8] = value
                    nextDayMap.compute(6) {_, oldVal -> oldVal?.plus(value) }
                    numberOfFish += value
                }
            }
            map = HashMap(nextDayMap)
        }
        return numberOfFish
    }


//  test if implementation meets criteria from the description, like:
    val testInput = readInput("Day06_test").first().split(",").map { it.toInt() }
    check(part1(testInput) == 5934)
    check(part2(testInput) == 26984457539)

    val input = readInput("Day06").first().split(",").map { it.toInt() }
    println(part1(input))
    println(part2(input))
}
