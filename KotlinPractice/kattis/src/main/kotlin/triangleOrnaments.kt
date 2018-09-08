import java.util.*

fun main(args: Array<String>) {
    val scanner = Scanner(System.`in`)

    val n = scanner.nextInt()
    var sum  = 0.0

    for (i in 1..n) {
        val a = scanner.nextInt()
        val b = scanner.nextInt()
        val c = scanner.nextInt()

        val angleA = calculateAngleInDegrees(b, c, a)
        val angleB = calculateAngleInDegrees(a, c, b)

        println (angleA)
        println (angleB)

        val x = if (angleA > angleB) c * Math.cos(Math.toRadians(90.0 - angleB))
                else c * Math.cos(Math.toRadians(90.0 - angleA))
//        val x = calculate(a, angleB)
//        val y = calculate(b, angleA)

//        println (x)
//        println (y)
        sum += (2 * x)
    }

    println(sum)
}

fun calculate(x: Int, angle: Double): Double =
        if (angle > 90)
            x * Math.cos(Math.toRadians(180.0 - angle))
        else
            x * Math.sin(Math.toRadians(90.0 - angle))

fun calculateAngleInDegrees(a : Int, b: Int, c : Int) : Double =
        Math.toDegrees(Math.acos((a * a + b * b - c * c) / (2.0 * a * b)))
