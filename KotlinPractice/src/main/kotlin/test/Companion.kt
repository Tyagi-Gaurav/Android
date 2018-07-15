package test

class MyUser(val nickName : String) {
    companion object {
        fun newSubscribingUser(email : String) =
                MyUser(email.substringBefore('@'))

        fun newFacebookUser(accountId : Int) {
//            User(getFacbookName(accountId))
        }
    }
}

fun main(args: Array<String>) {
    val newSubscribingUser = MyUser.newSubscribingUser("abc@dnail.com")
}