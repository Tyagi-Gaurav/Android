package pc

import org.assertj.core.api.Java6Assertions.assertThat
import org.junit.Test
import pc.ParentalControlLevel.*
import java.util.*

class ParentalControlLevelTest {

    @Test
    fun shouldHaveValidParentalControlValues() {
        assertThat(values()).isEqualTo(
                Arrays.asList(U,
                PG,
                TWELVE,
                FIFTEEN,
                EIGHTEEN).toTypedArray()
        )
    }

    @Test
    fun shouldConvertFromStringToParentalControlLevel() {
        assertThat(ParentalControlLevel.from("18")).isEqualTo(EIGHTEEN)
    }

    @Test
    fun shouldConvertFromStringToParentalControlLevelForAnyCase() {
        assertThat(ParentalControlLevel.from("u")).isEqualTo(U)
    }

    @Test
    fun shouldReturnEmptyWhenUnableToFindParentalControlLevel() {
        assertThat(ParentalControlLevel.from("XX")).isNull()
    }

    @Test
    fun shouldCompareAndReturnTrueWhenAGreaterThanB() {
        assertThat(ParentalControlLevel.EIGHTEEN.isGreaterThanOrEqualTo(
                ParentalControlLevel.U
        )).isTrue()
    }

    @Test
    fun shouldCompareAndReturnFalseWhenANotGreaterThanB() {
        assertThat(ParentalControlLevel.U.isGreaterThanOrEqualTo(
                ParentalControlLevel.EIGHTEEN
        )).isFalse()
    }

    @Test
    fun shouldCompareAndReturnFalseWhenAEqualToB() {
        assertThat(ParentalControlLevel.U.isGreaterThanOrEqualTo(
                ParentalControlLevel.U
        )).isTrue()
    }
}