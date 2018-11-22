import java.lang.Math.min
import java.util.*

fun main(args: Array<String>) {
    val scanner = Scanner(System.`in`)

    val nextLine = scanner.nextLine()

    val findPossibleSubstringsOf = findPossibleSubstringsOf(nextLine)
    val evaluate = evaluate(findPossibleSubstringsOf, nextLine) ?: nextLine.length
    println (min(nextLine.length, evaluate))
    //println (determineScore("EEN", "WNEENWEENEENE"))
}

fun evaluate(findPossibleSubstringsOf: MutableList<String>, nextLine: String?): Int? =
    findPossibleSubstringsOf
            .map { x -> determineScore(x, nextLine) }
            .min()

fun determineScore(subString : String, nextLine : String?): Int =
    nextLine!!.replace(subString, "M").length + subString.length


fun find(nextLine: String?, num: Int): List<String> {
    var i = 0
    val result = mutableListOf<String>()
    val len = nextLine!!.length
    while (i < len) {
        if (i + num <= len) {
            result.add(nextLine.substring(i, i + num))
        }

        i++
    }

    return result
}

fun findPossibleSubstringsOf(nextLine: String?): MutableList<String> {
    val result = mutableListOf<String>()
    for (i in 0 until nextLine!!.length) {
        result.addAll(find(nextLine, i + 1))
    }

    return result
}
