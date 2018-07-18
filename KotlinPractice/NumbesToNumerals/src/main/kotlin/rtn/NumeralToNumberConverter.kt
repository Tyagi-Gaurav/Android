package rtn

object NumeralToNumberConverter {
    val convMap : Map<Char, Pair<Int, Int>> = mapOf(
            'I' to Pair(1, 1),
            'V' to Pair(2, 5),
            'X' to Pair(3, 10),
            'L' to Pair(4, 50),
            'C' to Pair(5, 100),
            'D' to Pair(6, 500),
            'M' to Pair(7, 1000)
    )

    fun toNumber(input: String): Int {
        var num = 0

        for ((index, ch) in input.withIndex()) {
            val cPos = getPosition(ch)
            val nPos = if (index + 1 < input.length) getPosition(input[index+1]) else cPos

            if (cPos >= nPos)
                num += convMap.get(ch)?.second ?: 0
            else
                num -= convMap.get(ch)?.second ?: 0
        }

        return num
    }

    private fun getPosition(ch: Char) : Int =
            convMap.get(ch)?.first ?: throw IllegalArgumentException()
}
