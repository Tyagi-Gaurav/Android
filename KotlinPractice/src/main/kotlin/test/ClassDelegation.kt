package test

class DelegatingCollection<T>(innerList: Collection<T> = ArrayList<T>()) : Collection<T> by innerList {}

class CountingSet<T>(val innerSet : MutableCollection<T> = HashSet<T>()) :
        MutableCollection<T> by innerSet {
    var objectsAdded = 0

    override fun add(element: T) : Boolean {
        objectsAdded++
        return innerSet.add(element)
    }

    override fun addAll(elements: Collection<T>): Boolean {
        objectsAdded += elements.size
        return innerSet.addAll(elements)
    }
}