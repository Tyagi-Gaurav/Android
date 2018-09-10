import java.util.*

fun main(args: Array<String>) {
    val scanner = Scanner(System.`in`)

    val numberOfInputs = scanner.nextInt()
    var output = mutableListOf<String>()

    scanner.nextLine()
    for (i in 1..numberOfInputs) {
        output.add(check(scanner.nextLine()))
    }

    output.forEach { println(it) }
}

fun check(value: String) : String {
    val countMap = value.toLowerCase().groupingBy({ it }).eachCount()
    val stringBuilder = StringBuilder()
    for (i in 97..122) {
        if (!countMap.containsKey(i.toChar()))
            stringBuilder.append(i.toChar())
    }

    val result = stringBuilder.toString()
    return if (result.isEmpty())
        "pangram"
    else
        "missing $result"
}
