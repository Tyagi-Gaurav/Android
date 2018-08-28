package com.antz.starbuzz

data class Drink(val name: String, val description: String, val resourceId: Int) {
    companion object {
        val drinks = listOf<Drink>(
                Drink("Latte",
                        "A couple of espresso shots with steamed milk",
                        R.drawable.latte),
                Drink("Cappuccino", "Espresso, hot milk, and a steamed milk foam",
                        R.drawable.cappuccino),
                Drink("Filter", "Highest quality beans roasted and brewed fresh",
                        R.drawable.filter))
    }

    override fun toString(): String {
        return name
    }
}