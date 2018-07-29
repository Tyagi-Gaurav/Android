package gt

import java.io.File
import java.nio.file.Files

object LineCount {
    fun countFiles(directory: String): Long {
        return TraverseFile.traverse(directory, LineCount::countLines)
    }

    fun countLines(f : File) : Long =
        Files.readAllLines(f.toPath()).size.toLong()


}
