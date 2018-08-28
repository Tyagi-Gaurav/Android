package test

import java.util.*

fun <T> ensureTrailingPeriod(seq : T) where T : CharSequence, T : Appendable {
    if (!seq.endsWith('.')) {
        seq.append('.')
    }
}

class Processor<T> {
    fun process(value : T) {
        value?.hashCode()
    }
}

inline fun <reified T> loadService() {
    //return ServiceLoader.load(T::class.java)
}

//val serviceImpl = loadService<Service>()

//open class Animal {
//    fun feed() {}
//}
//
//class Herd<out T : Animal> { //Makes Animal Covariant
//    val size : Int = 0
//    operator fun get(i : Int) : T {return Animal()}
//}
//
//fun feedAll(animals : Herd<Animal>) {
//    for (i in 0 until animals.size) {
//        animals[i].feed()
//    }
//}
//
//class Cat : Animal() {
//    fun cleanLitter() {}
//}
//
//fun takeCareOfCats(cats : Herd<Cat>) {
//    for (i in 0 until cats.size) {
//        cats[i].cleanLitter()
//        feedAll(cats)
//    }
//}

fun printSum(c : Collection<*>) {
    val intList = c as? List<Int> ?: throw IllegalArgumentException("List expected")
    println (intList.sum())
}

fun main(args: Array<String>) {
    val seq = StringBuilder("hello world")
    ensureTrailingPeriod(seq)
    println(seq)

    printSum(listOf(2,5,7))
    //throws class cast instead of illegal argument
    printSum(listOf("a", "b"))
}