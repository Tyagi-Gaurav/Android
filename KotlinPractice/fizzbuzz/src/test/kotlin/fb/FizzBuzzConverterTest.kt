package fb

import org.assertj.core.api.Assertions
import org.junit.Test

class FizzBuzzConverterTest {
    @Test
    fun shouldPrintFizzBuzzWhenNumberDivisibleBy15() {
        Assertions.assertThat(FizzBuzzConverter.doWork(15))
                .isEqualTo(FizzBuzzDomain(FizzBuzzTypeEnum.FIZZBUZZ))
    }

    @Test
    fun shouldPrintFizzBuzzWhenNumberDivisibleBy5() {
        Assertions.assertThat(FizzBuzzConverter.doWork(5))
                .isEqualTo(FizzBuzzDomain(FizzBuzzTypeEnum.BUZZ))
    }

    @Test
    fun shouldPrintFizzBuzzWhenNumberDivisibleBy3() {
        Assertions.assertThat(FizzBuzzConverter.doWork(6))
                .isEqualTo(FizzBuzzDomain(FizzBuzzTypeEnum.FIZZ))
    }

    @Test
    fun shouldOutputLuckyWhenNumberContains3() {
        Assertions.assertThat(FizzBuzzConverter.doWork(30))
                .isEqualTo(FizzBuzzDomain(FizzBuzzTypeEnum.LUCKY))
    }

    @Test
    fun shouldOutputNumberWhenNumberDoesNotBelongToKeyCategory() {
        Assertions.assertThat(FizzBuzzConverter.doWork(2))
                .isEqualTo(FizzBuzzDomain(FizzBuzzTypeEnum.INTEGER, 2))
    }
}