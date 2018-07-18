package rtn

import common.Fixture
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.experimental.theories.DataPoints
import org.junit.experimental.theories.Theories
import org.junit.experimental.theories.Theory
import org.junit.runner.RunWith

@RunWith(Theories::class)
class NumeralToNumberConversionTest {
    companion object {
        @DataPoints
        @JvmField
        val fixture = arrayOf(
                Fixture("I", "1"),
                Fixture("III", "3"),
                Fixture("V", "5"),
                Fixture("VI", "6"),
                Fixture("X", "10"),
                Fixture("XI", "11"),
                Fixture("IV", "4"),
                Fixture("IX", "9"),
                Fixture("XIX", "19"),
                Fixture("XXXII", "32"),
                Fixture("XXXIX", "39"),
                Fixture("XL", "40"),
                Fixture("XLIV", "44"),
                Fixture("LXXVIII", "78"),
                Fixture("XC", "90"),
                Fixture("CD", "400")
        )
    }

    @Theory
    fun shouldConvertNumeralToNumber(fixture : Fixture) {
        assertThat(NumeralToNumberConverter.toNumber(fixture.input))
                .isEqualTo(fixture.expected.toInt())
    }

    @Test(expected = IllegalArgumentException::class)
    fun shouldThrowExceptionWhenInputHasInvalidCharacter() {
        NumeralToNumberConverter.toNumber("AXX")
    }
}