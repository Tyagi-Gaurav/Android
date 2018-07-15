package test

val map = hashMapOf<Int, String>(1 to "one", 2 to "two", 3 to "three")
val list = listOf<Int>(1 , 2 , 3 )

fun main(args : Array<String>) {
    println (map)
    println(list.joinToString(list, ";", "", ""))
    println(list.joinToString(list, separator = " ", prefix = "$" , postFix = "£"))
    println(list.joinToString(list))
    println(list.joinToString(list, "-"))
    println (1 plus 2)
}

fun <T> Collection<T>.joinToString(collection: Collection<T>, separator : String = ", ",
                     prefix : String = "", postFix : String = "") : String {
    val result = StringBuilder(prefix)

    for ((index, value) in collection.withIndex()) {
        if (index > 0) result.append(separator)
        result.append(value)
    }
    result.append(postFix)
    return result.toString()
}

infix fun Int.plus(other: Int) = this + other

