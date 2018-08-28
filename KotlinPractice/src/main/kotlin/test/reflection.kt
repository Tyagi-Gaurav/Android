package test

class person_reflect(val name: String, val age : Int)

fun foo(x : Int) = println(x)

fun main(args: Array<String>) {
    val person = person_reflect("Alice", 20)
    val kotlin = person.javaClass.kotlin

    println (kotlin.simpleName)
    println (kotlin.qualifiedName)
    kotlin.members.forEach {println (it.name)}

    val kfunction = ::foo

    kfunction.call(2)
}