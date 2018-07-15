package test

import test.Expr.*

//interface Expr
//class Num(val value : Int) : Expr //Class implementing Expr
//class Sum(val left : Expr, val right : Expr) : Expr
//

sealed class Expr {
    class Num(val value : Int) : Expr() //Class implementing Expr
    class Sum(val left : Expr, val right : Expr) : Expr()
}

fun eval(e : Expr) : Int{
    if (e is Num) {
        val n = e as Num //Redundant Cast
        return n.value
    }
    if (e is Expr.Sum) {
        return eval(e.left) + eval(e.right)
    }
    throw IllegalArgumentException("Invalid Expression")
}

fun eval1(e : Expr) : Int =
    if (e is Num) {
        val n = e as Num //Redundant Cast
        n.value
    } else if (e is Sum) {
        eval1(e.left) + eval1(e.right)
    }
    else {
        throw IllegalArgumentException("Invalid Expression")
    }

fun eval_withWhen(e : Expr) : Int =
        when (e) {
            is Num -> e.value
            is Sum -> eval_withWhen(e.left) + eval_withWhen(e.right)
        }

fun main(args : Array<String>) {
    println (eval(Sum(Sum(Num(1), Num(2)), Num(4))))
    println (eval1(Sum(Sum(Num(1), Num(2)), Num(4))))
    println (eval_withWhen(Sum(Sum(Num(1), Num(2)), Num (4))))
}