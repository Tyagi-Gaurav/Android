package fb

import fb.FizzBuzzTypeEnum.INTEGER

data class FizzBuzzDomain(val type : FizzBuzzTypeEnum,
                     val value : Int = 0) {
    override fun toString(): String {
        return when {
            type == INTEGER -> value.toString()
            else -> type.toString().toLowerCase()
        }
    }
}
