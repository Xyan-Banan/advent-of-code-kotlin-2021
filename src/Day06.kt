fun main() {

    fun fishLife(input: List<Int>, days: Int): Long {
        var fish = Array(9) {age ->  input.count {it == age}.toLong() }
        val nextDayFish = Array(9) { 0L }
        var numberOfFish = input.size.toLong()
        repeat(days) {
            for (i in fish.indices.reversed()) {
                val value = fish[i]
                if (i > 0)
                    nextDayFish[i - 1] = value
                else {
                    nextDayFish[8] = value
                    nextDayFish[6] += value
                    numberOfFish += value
                }
            }
            fish = nextDayFish.clone()
        }
        return numberOfFish
    }

    fun part1(input: List<Int>) = fishLife(input, 80).toInt()

    fun part2(input: List<Int>) = fishLife(input,256)



//  test if implementation meets criteria from the description, like:
    val testInput = readIntList("Day06_test", ",")
    check(part1(testInput) == 5934)
    check(part2(testInput) == 26984457539)

    val input = readIntList("Day06", ",")
    println(part1(input))
    println(part2(input))
}
