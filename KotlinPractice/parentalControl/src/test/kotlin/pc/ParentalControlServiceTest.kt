package pc

import io.mockk.every
import io.mockk.mockk
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class ParentalControlServiceTest {
    val movieService : MovieService = mockk()
    val parentalControlService: ParentalControlService = ParentalControlServiceImpl(movieService)

    val movieId = "XX"

    @Test(expected = ParentalControlServiceException::class)
    fun shouldThrowException_whenMovieIdIsInvalid() {
        //given
        val parentalControlLevel = "YY"
        every { movieService.getParentalControlLevel(movieId) } throws TitleNotFoundException()

        //when
        parentalControlService.getParentalControlLevel(parentalControlLevel, movieId)
    }

    @Test(expected = ParentalControlServiceException::class)
    fun shouldThrowExceptionWhenMovieServiceFails() {
        //given
        val parentalControlLevel = "YY"
        every { movieService.getParentalControlLevel(movieId) } throws TechnicalFailureException()

        //when
        parentalControlService.getParentalControlLevel(parentalControlLevel, movieId)
    }

    @Test
    fun shouldFailInFavourOfCustomerWhenMovieServiceReturnsInvalidMovieLevel() {
        //given
        val parentalControlLevel = "YY"
        every { movieService.getParentalControlLevel(movieId) } returns "X"

        //when
        val isAllowed = parentalControlService.getParentalControlLevel(parentalControlLevel, movieId)

        //then
        assertThat(isAllowed).isTrue()
    }

    @Test
    fun shouldAllowToWatchMovieWhenCustomerPCLIsHigher() {
        //given
        val parentalControlLevel = "18"
        every { movieService.getParentalControlLevel(movieId) } returns "12"

        //when
        val result = parentalControlService.getParentalControlLevel(parentalControlLevel, movieId)

        //then
        assertThat(result).isTrue()
    }

    @Test
    fun shouldNotAllowToWatchMovieWhenCustomerPCLIsLower() {
        //given
        val parentalControlLevel = "12"
        every { movieService.getParentalControlLevel(movieId) } returns "18"

        //when
        val result = parentalControlService.getParentalControlLevel(parentalControlLevel, movieId)

        //then
        assertThat(result).isFalse()
    }

    @Test
    fun shouldAllowToWatchMovieWhenCustomerPCLIsEqual() {
        //given
        val parentalControlLevel = "PG"
        every { movieService.getParentalControlLevel(movieId) } returns "PG"

        //when
        val result = parentalControlService.getParentalControlLevel(parentalControlLevel, movieId)

        //then
        assertThat(result).isTrue()
    }
}
