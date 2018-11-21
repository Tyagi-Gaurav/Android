import java.util.*

fun main(args: Array<String>) {
    val scanner = Scanner(System.`in`)

    val n = scanner.nextInt()

    val digits = getDigits(n)
    val permutations = permute(digits)
    val min = generateNumbers(permutations, digits.size)
            .filter { x -> x > n }
            .map { x -> x - n }
            .min()

    println (min?.plus(n) ?: 0)
}

fun generateNumbers(permutations: List<List<Int>>, n: Int): List<Int> =
        permutations.map { x -> calculateSum(x, n) }

fun calculateSum(x: List<Int>, n: Int): Int {
    var sum = 0
    for (i in 0 until n) {
        sum = sum * 10 + x[i]
    }

    return sum
}

fun permute(digits: List<Int>): List<List<Int>> {
    if (digits.isEmpty()) return emptyList()

    val element = digits.take(1)
    val permute = permute(digits.drop(1))

    return if (permute.isEmpty())
        listOf(element)
    else {
        val result = mutableListOf<List<Int>>()
        for (input in permute) {
            result.addAll(combine(element[0], input))
        }

        result
    }
}

fun combine(a: Int, b: List<Int>): List<List<Int>> {
    val result = mutableListOf<List<Int>>()
    for (i in 0..b.size) {
        result.add(b.take(i).plus(a).plus(b.drop(i)))
    }

    return result
}

fun getDigits(n: Int): List<Int> =
        if (n == 0) emptyList() else
            listOf(n % 10).plus(getDigits(n / 10))


