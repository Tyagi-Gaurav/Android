package ntr

import java.util.*

object NumberToNumeralConverter {
    val conversionTable = mapOf<Int, String>(
            1 to "I",
            5 to  "V",
            10 to  "X",
            50 to  "L",
            100 to  "C",
            500 to "D",
            1000 to "M"
    )

    val sct = conversionTable.toSortedMap()

    fun toNumeral(num: Int): String {
        val value = sct.get(num)
        return value ?: digitConversion(num, sct)
    }

    private fun digitConversion(num: Int, sct: SortedMap<Int, String>): String {
        var a = num
        val sb = StringBuilder()

        if (a > 0) {
            loop@ for ((key, value) in sct) {
                val result = num/key
                val rem = num%key
                if (result > 0 && result <= 3) {
                    if (!skipWhenRepeatRestricted(result, value)) {
                        val repeat = value.repeat(result)
                        val remConversion = digitConversion(rem, sct)
                        if (!containsVCD(repeat, remConversion)) {
                            sb.append(repeat)
                            sb.append(remConversion)
                            break@loop
                        }
                    }
                } else if (result == 0) {
                    val rem = num % 10
                    if (rem > 0 && num > 10) {
                        sb.append(digitConversion(num - rem, sct))
                        sb.append(digitConversion(rem, sct))
                    } else {
                        sb.append(sct.get(key - num))
                        sb.append(value)
                    }
                    break@loop
                }
            }
        }

        return sb.toString()
    }

    private fun containsVCD(repeat: String, remConversion: String): Boolean {
        fun a(x: String) : Boolean = repeat.contains(x) && remConversion.contains(x)
        return (a("V") || a("D"))
    }

    private fun skipWhenRepeatRestricted(cr: Int, value: String): Boolean =
        (value == "V" || value == "D") && (cr >= 2)
}

