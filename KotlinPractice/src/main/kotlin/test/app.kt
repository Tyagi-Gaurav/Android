package test

import test.Color.*

//Block Body Function
fun main(args: Array<String>) {
    val name = if (args.size > 0) args[0] else "Kotlin!"
    println ("Hello World! from $name")
    val maxVal = max(1, 5)
    println ("Max 1 and 5 is $maxVal")
    val p = Person("test")
    println (p.name)
    val r = Rectangle(10, 10)
    println (r.isSquare)
    println (getMnemonic(YELLOW))
    println (mix(RED, YELLOW))
    //println (mix(VIOLET, YELLOW)) //Causes Exception
    range()
}

fun range() {
    for (i in 1..10) print(fizzBuzz(i))
}

fun fizzBuzz(i : Int) = when {
    i % 15 == 0 -> "FizzBuzz"
    i % 3 == 0 -> "Fizz"
    i % 5 == 0 -> "Buzz"
    else -> "$i"
}

//Expression Body Function
fun max(a :Int, b: Int) : Int = if (a > b) a else b

class Rectangle (val width : Int, val height : Int) {
    val isSquare : Boolean
        get() = height == width
}

enum class Color {
    YELLOW, RED, VIOLET
}

fun getMnemonic(c : Color) : String {
    return when (c) {
        YELLOW -> "yellow"
        RED -> "red"
        Color.VIOLET -> "Violet"
    }
}

fun mix(c1 : Color, c2 : Color) =
    when (setOf(c1, c2))  {
        setOf(YELLOW, RED) -> "orange"
        else -> throw Exception("Dirty Color")
    }
