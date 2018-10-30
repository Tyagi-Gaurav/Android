import java.util.*

fun main(args: Array<String>) {
    val scanner = Scanner(System.`in`)

    val b = scanner.nextInt()
    val br = scanner.nextInt()
    val bs = scanner.nextInt()
    val a = scanner.nextInt()
    val aS = scanner.nextInt()

    var sum = 0
    var ar = a
    val r1 = (br - b) * bs

    while (sum <= r1) {
        sum += aS
        ar++
    }

    println(ar)
}