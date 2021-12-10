fun main() {
    data class Board(val data: List<Int>) {
        private val markedNumbers = MutableList(data.size) { false }
        val isWin get() = checkRows() or checkColumns()

        private fun checkRows() = markedNumbers.windowed(5, 5).any { row -> row.all { it } }

        private fun checkColumns(): Boolean {
            return (0..4).map { markedNumbers.slice(it until markedNumbers.size step 5) }
                .any { row -> row.all { it } }
        }

        fun checkNumber(number: Int): Boolean {
            val index = data.indexOf(number)
            if (index >= 0) {
                markedNumbers[index] = true
                return true
            }
            return false
        }

        fun dropMarked() = markedNumbers.fill(false)

        fun sunUnmarked() = data.filterIndexed { index, _ -> !markedNumbers[index] }.sum()
    }

    data class Bingo(val numbers: List<Int>, val boards: List<Board>)

    fun List<String>.getNumbersAndBoards(): Bingo {
        val numbers = first().split(",").map { it.toInt() }
        val boardsLines = drop(2).windowed(5, 6)
        val boards = boardsLines.map { board ->
            Board(board.flatMap { it.split(" ").filter { it.isNotBlank() }.map { it.toInt() } })
        }
        return Bingo(numbers,boards)
    }

    fun part1(numbers: List<Int>, boards: List<Board>): Int {
        for (number in numbers) {
            for (board in boards) {
                if (board.checkNumber(number))
                    if (board.isWin)
                        return number * board.sunUnmarked()
            }
        }
        return -1
    }

    fun part2(numbers: List<Int>, boards: List<Board>): Int {
        val filteredBoards = boards.toMutableList()
        for (number in numbers) {
            for (board in filteredBoards) {
                board.checkNumber(number)
            }
            if(filteredBoards.size > 1)
                filteredBoards.removeIf { it.isWin }
            else
                if(filteredBoards.first().isWin)
                    return number * filteredBoards.first().sunUnmarked()
        }
        return -1
    }

//     test if implementation meets criteria from the description, like:
    val (testNumbers, testBoards) = readInput("Day04_test").getNumbersAndBoards()
    check(part1(testNumbers, testBoards) == 4512)
    testBoards.forEach { it.dropMarked() }
    check(part2(testNumbers, testBoards) == 1924)
//
    val (numbers, boards) = readInput("Day04").getNumbersAndBoards()
    println(part1(numbers, boards))
    boards.forEach { it.dropMarked() }
    println(part2(numbers, boards))
}
