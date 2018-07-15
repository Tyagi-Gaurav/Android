package pc

interface ParentalControlService {
    fun getParentalControlLevel(customerParentalControlLevel : String ,
                                movieId : String) : Boolean
}
