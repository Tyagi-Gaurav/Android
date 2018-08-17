package test

data class Person(val name: String, val age: Int)

val people = listOf(Person("Alice", 29), Person("Bob", 31))

val findOldest1 = people.stream().max { o1, o2 -> o1.age - o2.age}
val findOldest2 = people.maxBy(Person::age)
val findOldest3 = people.maxBy {p -> p.age}

fun main(args: Array<String>) {
    println (findOldest1)
    println (findOldest2)
    println (findOldest3)

    run {println (42)}

    println ("ab1c".filter { it in 'a'..'z' })
}

fun String.filter(predicate : (Char) -> Boolean) : String {
    val sb = StringBuilder()

    for (index in 0 until length) {
        val element = get(index)
        if (predicate(element)) sb.append(element)
    }

    return sb.toString()
}

fun <T> Collection<T>.joinToString(
        separator : String = " ",
        prefix: String = "",
        postfix : String = "",
        transform: (T) -> String = {it.toString()}) : String {
    val result = StringBuilder(prefix)

    for ((index, element) in this.withIndex()) {
        if (index > 0) result.append(separator)
        result.append(transform(element))
    }

    result.append(postfix)
    return result.toString()
}