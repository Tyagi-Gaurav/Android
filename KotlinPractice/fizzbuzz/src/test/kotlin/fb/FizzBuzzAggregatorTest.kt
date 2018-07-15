package fb

import fb.FizzBuzzTypeEnum.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import java.util.Arrays.asList

class FizzBuzzAggregatorTest {
    val fba = FizzBuzzAggregator()

    @Test fun shouldAggregate() {
        //given
        val fbList = asList(
                FizzBuzzDomain(INTEGER, 1),
                FizzBuzzDomain(FIZZ),
                FizzBuzzDomain(BUZZ),
                FizzBuzzDomain(FIZZBUZZ),
                FizzBuzzDomain(INTEGER, 3),
                FizzBuzzDomain(FIZZBUZZ))

        val expectedResult = mapOf(
                INTEGER to 2,
                FIZZBUZZ to 2,
                FIZZ to 1,
                BUZZ to 1)

        //when
        val result = fba.aggregate(fbList)

        //then
        assertThat(result).isNotNull
        assertThat(result).isInstanceOf(Map::class.java)
        assertThat(result).isEqualTo(expectedResult)
    }
}