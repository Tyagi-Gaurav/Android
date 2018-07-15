package pc

class ParentalControlServiceImpl(val movieService: MovieService) : ParentalControlService {
    override fun getParentalControlLevel(customerParentalControlLevel: String,
                                         movieId: String): Boolean {
        try {
            val parentalControlLevel = movieService.getParentalControlLevel(movieId)

            val movieLevel = ParentalControlLevel.from(parentalControlLevel)
            val customerLevel = ParentalControlLevel.from(customerParentalControlLevel)

            val result = movieLevel?.let {
                customerLevel?.isGreaterThanOrEqualTo(movieLevel)
            }

            return result ?: true
        } catch (e : Exception) {
            throw ParentalControlServiceException(e.message, e)
        }
    }
}