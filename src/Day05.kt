fun main() {
//    found prototype of this function on stackoverflow
    infix fun Int.toward(to: Int) = if (this < to) this..to else to..this

    data class Point(val x: Int, val y: Int)
    data class VentLine(val start: Point, val end: Point) {
        val pointsOnLine: List<Point> by lazy {
            val points = mutableListOf<Point>()

            val p1 = end.x - start.x
            val p2 = end.y - start.y
            for (x in start.x toward end.x)
                for (y in start.y toward end.y)
                    if ((x - start.x) * p2 == (y - start.y) * p1)
                        points.add(Point(x, y))
            points
        }
        val isHorizontalOrVertical get() = (start.x == end.x) or (start.y == end.y)
    }

    fun part1(input: List<VentLine>): Int {
        return input.filter { it.isHorizontalOrVertical }.flatMap { it.pointsOnLine }.groupBy { it }
            .filter { it.value.size > 1 }.count()
    }

    fun part2(input: List<VentLine>): Int {
        return input.flatMap { it.pointsOnLine }.groupBy { it }.filter { it.value.size > 1 }.count()
    }

    fun List<String>.getVentLines(): MutableList<VentLine> {
        fun String.parsePoint() =  split(",").let { Point(it[0].toInt(), it[1].toInt()) }
        val ventLines = mutableListOf<VentLine>()
        for (line in this) {
            val (start, end) = line.split(" -> ").map { it.parsePoint() }
            ventLines.add(VentLine(start, end))
        }
        return ventLines
    }
//  test if implementation meets criteria from the description, like:
    val testInput = readInput("Day05_test").getVentLines()
    check(part1(testInput) == 5)
    check(part2(testInput) == 12)

    val input = readInput("Day05").getVentLines()
    println(part1(input))
    println(part2(input))
}
