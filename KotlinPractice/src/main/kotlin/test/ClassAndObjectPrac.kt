package test

interface Clickable {
    fun click()
    fun showOff() = println("I'm clickable!")
}

interface Focusable {
    fun setFocus(b : Boolean) = println ("I ${if (b) "got" else "lost"} focus")

    fun showOff() = println("I'm focussable!")
}

class Button : Clickable, Focusable {
    override fun click()  = println("I was clicked")

    override fun showOff() {
        super<Clickable>.showOff()
        super<Focusable>.showOff()
    }
}

fun main(args : Array<String>)  {
    Button().click()
    Button().showOff()
}

open class RichButton :  Clickable {
    final override fun click() {}
    fun disable() {}
    open fun animate() {
        //This method can be overriden in a sub-class
    }
}

class PoorButton : RichButton() {

}

/**
 * In addition, you need to add the open modifier to every property or method that can be overridden.
 */
abstract class Animated {
    abstract fun animate()

    open fun stopAnimating() {

    }

    fun animateTwice() {}
}

class User (_nickName : String) {
    val nickName : String

    init {
        nickName = _nickName
    }
}