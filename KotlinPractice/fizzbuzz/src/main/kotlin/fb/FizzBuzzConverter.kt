package fb

object FizzBuzzConverter {
    fun doWork(num: Int): FizzBuzzDomain {
        when {
            containsDigit(num, 3) -> return FizzBuzzDomain(FizzBuzzTypeEnum.LUCKY)
            num % 15 == 0 -> return FizzBuzzDomain(FizzBuzzTypeEnum.FIZZBUZZ)
            num % 5 == 0 -> return FizzBuzzDomain(FizzBuzzTypeEnum.BUZZ)
            num % 3 == 0 -> return FizzBuzzDomain(FizzBuzzTypeEnum.FIZZ)
            else -> return FizzBuzzDomain(FizzBuzzTypeEnum.INTEGER, num)
        }
    }

    private fun containsDigit(num: Int, i: Int): Boolean {
        if (num > 0 && num % 10 == i)
            return true
        else if (num == 0)
            return false
        else
            return containsDigit(num/10, i)
    }
}
