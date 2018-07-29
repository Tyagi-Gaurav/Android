package gt

object FileCount {
    fun countFiles(directory : String) : Long {
        return TraverseFile.
                traverse(directory, { 1})
    }
}
