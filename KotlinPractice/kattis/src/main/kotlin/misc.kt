import java.util.*

fun main(args: Array<String>) {
    val scanner = Scanner(System.`in`)

    val input = mutableListOf<Int>()

    for (i in 1..9) {
        input.add(scanner.nextInt())
    }

    val pairs = findPairs(input)
    val sum = input.sum()
    val findFirst = pairs.stream().filter { x -> sum - x.first - x.second == 100 }
            .findFirst().get()

    input.forEach { if (it != findFirst.first && it != findFirst.second) println(it)}
}

fun findPairs(input: MutableList<Int>): List<Pair<Int, Int>> {
    val mutableListOf = mutableListOf<Pair<Int, Int>>()
    for (i in 0 until input.size - 1) {
        for (j in i+1 until input.size) {
            mutableListOf.add(Pair(input[i], input[j]))
        }
    }
    return mutableListOf
}
