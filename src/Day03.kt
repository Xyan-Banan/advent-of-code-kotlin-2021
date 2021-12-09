fun main() {
    data class Counter(var ones: Int = 0, var zeros: Int = 0)

    fun part1(input: List<String>): Int {
        val counters = Array(input[0].length){Counter()}
        for(bits in input){
            for(i in 0 until bits.length){
                when(bits[i]){
                    '0' -> counters[i].zeros++
                    '1' -> counters[i].ones++
                }
            }
        }
        val gammaBits = counters.map { if (it.ones > it.zeros) 1 else 0 }.joinToString(separator = "")
        val gammaRate = gammaBits.toInt(2)
        val epsilonBits = gammaBits.map { if(it == '1') '0' else '1' }.joinToString(separator = "")
        val epsilonRate = epsilonBits.toInt(2)

        return gammaRate * epsilonRate
    }

    fun part2(input: List<String>): Int {
        var filtered = input
        var filter = ""
        var zeros: Int
        var ones: Int
        var i = 0
        while (filtered.size > 1){
            zeros = 0
            ones = 0
            for(bits in filtered){
                when(bits[i]){
                    '0' -> zeros++
                    '1' -> ones++
                }
            }
            filter += if(zeros > ones) '0' else '1'
            filtered = filtered.filter { it.startsWith(filter) }
            i++
        }
        val oxygenRating = filtered.first().toInt(2)
        println(oxygenRating)
        filtered = input
        filter = ""
        i = 0
        while (filtered.size > 1){
            zeros = 0
            ones = 0
            for(bits in filtered){
                when(bits[i]){
                    '0' -> zeros++
                    '1' -> ones++
                }
            }
            filter += if(zeros > ones) '1' else '0'
            filtered = filtered.filter { it.startsWith(filter) }
            i++
        }
        val CO2Rating = filtered.first().toInt(2)
        return oxygenRating * CO2Rating
    }

//     test if implementation meets criteria from the description, like:
    val testInput = readInput("Day03_test")
    check(part1(testInput) == 198)
    check(part2(testInput) == 230)
//
    val input = readInput("Day03")
    println(part1(input))
    println(part2(input))
}
