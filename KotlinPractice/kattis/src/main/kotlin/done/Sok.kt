package done

import java.util.*

fun main(args: Array<String>) {
    val scanner = Scanner(System.`in`)

    val bought = scanner.nextLine()
    val ratios = scanner.nextLine()

    val barray = bought.split(" ")
    val rarray = ratios.split(" ")
    val zipList = barray.zip( rarray )
    var min_ratio = zipList.map { p -> p.first.toDouble()/p.second.toDouble() }.min()

    min_ratio?.let {
        val result = zipList.map { x -> x.first.toDouble() - (x.second.toDouble() * min_ratio) }
                .toList()

        val s = StringBuilder()

        println (result.joinTo(s, separator = " "))
    }
}