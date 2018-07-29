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
}
