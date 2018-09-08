package com.antz.bitsandpizzas

class Pizza(val name: String, val resourceId: Int) {
    companion object {
        val pizzas = listOf(
                Pizza("Diavolo", R.drawable.diavolo),
                Pizza("Funghi", R.drawable.funghi)
        )
    }
}
