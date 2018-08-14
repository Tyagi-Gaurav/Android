package done

import java.util.*
import kotlin.Comparator

fun main(args : Array<String>) {
    val scanner = Scanner(System.`in`)

    var N = scanner.nextInt()
    var result = mutableListOf<String>()
    var num = 0

    while (N > 0) {
        scanner.nextLine()
        var mapResult = mutableMapOf<String, Int>()
        num += 1
        for (i in 1..N) {
            val nextLine = scanner.nextLine()
            val split = nextLine.split(" ")

            val name = split.last()
            mapResult.compute(name.toLowerCase()) { _, v -> v?.inc() ?: 1}
        }

        N = scanner.nextInt()
        result.add("List $num:")
        mapResult.toSortedMap(Comparator.naturalOrder<String>())
                .forEach { result.add("${it.key} | ${it.value}") }
    }

    result.forEach { println (it) }

}
