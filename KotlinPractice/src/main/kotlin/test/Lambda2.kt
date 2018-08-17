package test

fun main(args: Array<String>) {
    val calculator = getShippingCostCalculator(Delivery.EXPEDITED)
    println (calculator(Order(3)))
}

enum class Delivery {STANDARD, EXPEDITED}

class Order(val itemCount : Int)

fun getShippingCostCalculator(delivery: Delivery) : (Order) -> Double {
    if (delivery == Delivery.EXPEDITED) {
        return {order -> 6 + 2.1 * order.itemCount}
    }

    return {order -> 1.2 * order.itemCount}
}