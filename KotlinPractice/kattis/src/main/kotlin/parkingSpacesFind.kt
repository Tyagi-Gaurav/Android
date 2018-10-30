import java.util.*

fun main(args: Array<String>) {
    val scanner = Scanner(System.`in`)

    val row = scanner.nextInt()
    val column = scanner.nextInt()
    val inputArray = Array(row) { CharArray(column) }

    scanner.nextLine()
    for (i in 0 until row) {
        val element = scanner.nextLine()
        inputArray[i] = element.toCharArray()
    }

    var result = mutableListOf<Int>()

    for (i in 0..50) {
        result.add(0)
    }

    for (i in 0..row - 2) {
        for (j in 0..column - 2) {
            val countXIn = countChIn(inputArray, i, j, 'X')
            val countHashIn = countChIn(inputArray, i, j, '#')
            if (countHashIn == 0 && countXIn >= 0) {
                result[countXIn] = result[countXIn]+1
            }
        }
    }

    result.stream().limit(5).forEach { println(it) }
}

fun countChIn(inputArray: Array<CharArray>, i: Int, j: Int, ch : Char): Int {
    var count = 0

    if (inputArray[i][j] == ch) count++;
    if (inputArray[i+1][j] == ch) count++;
    if (inputArray[i][j+1] == ch) count++;
    if (inputArray[i+1][j+1] == ch) count++;

    return count
}
