fun main() {
    fun List<List<Int>>.getNeighbours(row: Int, col: Int): List<Int> {
        return listOf(
            get(row).getOrElse(col - 1) { 9 },
            get(row).getOrElse(col + 1) { 9 },
            getOrNull(row - 1)?.get(col) ?: 9,
            getOrNull(row + 1)?.get(col) ?: 9
        )
    }

    fun part1(input: List<List<Int>>): Int {
//        val numbers = input.flatten()
//        val rowSize = input.first().size
        var sum = 0
        for ((rowInd, row) in input.withIndex()) {
            for ((colInd, number) in row.withIndex()) {
                if (input.getNeighbours(rowInd, colInd).all { it > number }) {
                    sum += number + 1
                }
            }
        }
        return sum
    }

    data class Point(val x: Int, val y: Int)
    data class Basin(val points: Set<Point>)

    fun List<List<Int>>.getValue(row: Int, col: Int, default: Int = 9) =
        getOrNull(row)?.getOrElse(col) { default } ?: default

    val basins = mutableListOf<Basin>()
    fun List<List<Int>>.getBasin(row: Int, col: Int): Set<Point> {
//        val value =
        if (getValue(row, col) == 9)
            return emptySet()

        val isPointInBasins = basins.any { Point(row, col) in it.points }
        if (isPointInBasins)
            return emptySet()

        val points = setOf<Point>()
        points.union(getBasin(row - 1, col))
        points.union(getBasin(row, col - 1))
        points.union(getBasin(row, col + 1))
        points.union(getBasin(row + 1, col))
        return points
    }

    fun part2(input: List<List<Int>>): Int {
        for ((rowInd, row) in input.withIndex()) {
            for ((colInd, _) in row.withIndex()) {
                input.getBasin(rowInd, colInd).also {
                    if(it.isNotEmpty()){
                        basins.add(Basin(it))
                    }
                }
            }
        }
        return basins.sortedByDescending { it.points.size }.take(3).fold(1) { acc, it ->  acc * it.points.size }
    }


//  test if implementation meets criteria from the description, like:
    val testInput = readInput("Day09_test").map { it.map { it.digitToInt() } }
    check(part1(testInput) == 15)
    check(part2(testInput) == 1134)

    val input = readInput("Day09").map { it.map { it.digitToInt() } }
    println(part1(input))
    println(part2(input))
}
