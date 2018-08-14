package test

data class Point(val x : Int, val y : Int) {
    operator fun plus (other : Point) : Point {
        return Point(x + other.x, y + other.y)
    }

    operator fun unaryMinus() : Point {
        return Point(-x, -y)
    }
}

fun main(args: Array<String>) {
    val p = Point(2, 3)
    val q = Point(5, 6)

    val x = p + q
    println (x)
    println (-x)
}