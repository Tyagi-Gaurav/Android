package ntr

import common.Fixture
import org.assertj.core.api.Assertions.assertThat
import org.junit.experimental.theories.DataPoints
import org.junit.experimental.theories.Theories
import org.junit.experimental.theories.Theory
import org.junit.runner.RunWith

@RunWith(Theories::class)
class NumberToNumeralConverterTest {
    companion object {
        @DataPoints
        @JvmField
        val FIXTURES = arrayOf(
                Fixture("1", "I"),
                Fixture("2", "II"),
                Fixture("5", "V"),
                Fixture("6", "VI"),
                Fixture("7", "VII"),
                Fixture("10", "X"),
                Fixture("11", "XI"),
                Fixture("36", "XXXVI"),
                Fixture("4", "IV"),
                Fixture("9", "IX"),
                Fixture("40", "XL"),
                Fixture("41", "XLI"),
                Fixture("44", "XLIV"),
                Fixture("49", "XLIX"),
                Fixture("59", "LIX"),
                Fixture("64", "LXIV"),
                Fixture("89", "LXXXIX"),
                Fixture("200", "CC"),
                Fixture("400", "CD"),
                Fixture("900", "CM"),
                Fixture("2018", "MMXVIII"),
                Fixture("2019", "MMXIX")

        )
    }

    @Theory
    fun shouldConvertNumberToRoman(fixture: Fixture) {
        val numeral = NumberToNumeralConverter.toNumeral(fixture.input.toInt())
        assertThat(numeral)
                .describedAs("${fixture.expected} is not same as $numeral")
                .isEqualTo(fixture.expected)
    }
}