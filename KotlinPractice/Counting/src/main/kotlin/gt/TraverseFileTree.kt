package gt

import java.io.File
import java.nio.file.Files
import java.nio.file.Paths

object TraverseFile {
    fun traverse(directory : String,
                 count : (f :File) -> Long) : Long {
        val file = File(directory)

        file?.let {
            val path = Paths.get(file.toURI())
            val newDirectoryStream = Files.newDirectoryStream(path)
            return newDirectoryStream
                    .map { if (!it.toFile().isDirectory)
                        count.invoke(it.toFile())
                    else
                        traverse(it.toString(), count)}
                    .sum()
        }

        return 0
    }
}