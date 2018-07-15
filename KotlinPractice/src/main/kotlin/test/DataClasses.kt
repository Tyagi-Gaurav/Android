package test

class Client(val name: String, val postCode : Int) {
    override fun toString(): String {
        return "Client(name='$name', postCode=$postCode)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Client

        if (name != other.name) return false
        if (postCode != other.postCode) return false

        return true
    }

    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + postCode
        return result
    }
}

data class Client2(val name : String, val postCode: Int) {
    //Already generates equals, toString and hashcode
}

fun main(args: Array<String>) {
    val client1 = Client("c1", 12)
    val client2 = Client("c1", 12)
    val client3 = Client2("c1", 12)
    println(client1.toString())
    println(client3.toString())
    println (client1 == client2)
}