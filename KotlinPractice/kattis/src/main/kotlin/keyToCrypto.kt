import java.util.*

fun main(args: Array<String>) {
    val scanner = Scanner(System.`in`)

    val cipherText = scanner.nextLine()
    val key = scanner.nextLine()

    val charList = cipherText.toCharArray().toList()
    var keyList = key.toMutableList()
    val result = StringBuilder()

    for ((index, value) in charList.withIndex()) {
        val x = leftShift(value, keyList.get(index).toInt() - 65)
        result.append(x)
        keyList.add(x)
    }

    println(result.toString())
}

fun leftShift(x: Char, i: Int) : Char {
    val o = x.toInt() - 65
    val newP = 65 + (26 + (o - i)) % 26
    return newP.toChar()
}
