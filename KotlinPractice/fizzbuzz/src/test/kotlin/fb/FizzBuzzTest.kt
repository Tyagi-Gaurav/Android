package fb

import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test as test

class FizzBuzzTest {
    val fba =  mockk<FizzBuzzAggregator>()

    val fb = Fizzbuzz(fba)

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
    }

    @test fun shouldPrintFizzBuzzForLongSequence() {
        every { fba.aggregate(any()) } returns (mapOf(FizzBuzzTypeEnum.INTEGER to 10,
                        FizzBuzzTypeEnum.FIZZ to 4,
                        FizzBuzzTypeEnum.BUZZ to 3,
                        FizzBuzzTypeEnum.FIZZBUZZ to 1,
                        FizzBuzzTypeEnum.LUCKY to 2))

        assertThat(fb.evaluate(1, 20))
                .isEqualTo("1 2 lucky 4 buzz fizz 7 8 fizz buzz 11 fizz lucky 14 fizzbuzz 16 17 fizz 19 buzz" +
                        " fizz: 4 buzz: 3 fizzbuzz: 1 lucky: 2 integer: 10")

        verify  {fba.aggregate(any())}
    }


}