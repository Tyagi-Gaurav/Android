
fun main(args: Array<String>) {
    val redRover = RedRover()
    redRover.encode("WNEENWEENEENE")
}

class RedRover {
    fun encode(input: String) {
        val root = createSuffixTree(input)
        //Determine maximum repeated String in descending order (greater than 2 repetitions)
        //For each repetition, find encoding
        //output best encoding
    }

    private fun createSuffixTree(input: String) : Node {
        val root = SuffixNode('$', mutableMapOf())
        val length = input.length
        for (i in 1..length) {
            val substring = input.substring(length - i, length)
            root.addSuffix(substring)
        }
        return root
    }

}

abstract class Node {
    abstract fun addSuffix(substring: String)
}

class SuffixNode(val value : Char, val childs : Map<Char, Node>) : Node() {
    override fun addSuffix(substring: String) {
        if (substring.isNotEmpty()) {
            addSuffixInternal(substring, 0)
        }
    }

    fun addSuffixInternal(substring: String, index : Int) {
        if (childs.containsKey(substring[index])) {
            //Move to next char
            childs[substring[index]]?.addSuffix(substring)
        } else {

        }
    }
}

class LeafNode : Node() {
    override fun addSuffix(substring: String) {}
}