import java.util.*

fun main(args: Array<String>) {
    val scanner = Scanner(System.`in`)

    val n = scanner.nextInt()
    val T = scanner.nextInt()

    var sum = 0
    var count = 0

    for (i in 1..n) {
        val x = scanner.nextInt()

        if (sum + x <= T) {
            count ++
            sum += x
        } else {
            break
        }
    }

    println (count)
}