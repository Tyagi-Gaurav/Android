package pc

interface MovieService {
    @Throws(TitleNotFoundException::class, TitleNotFoundException::class)
    fun getParentalControlLevel(movieId : String) : String
}