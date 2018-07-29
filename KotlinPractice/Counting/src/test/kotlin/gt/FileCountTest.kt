package gt

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class FileCountTest {
    val BASE_FILE = "Counting/src/test/resources"

    @Test fun shouldReturnZeroOnEmptyDirectory() {
        assertThat(FileCount.countFiles("$BASE_FILE/empty")).isEqualTo(0)
    }

    @Test
    fun shouldReturnOneForDirectoryWithSingleFile() {
        assertThat(FileCount.countFiles("$BASE_FILE/oneFile")).isEqualTo(1)
    }

    @Test
    fun shouldReturnTwoForDirectoryWithTwoFiles() {
        assertThat(FileCount.countFiles("$BASE_FILE/twoFiles")).isEqualTo(2)
    }

    @Test
    fun shouldCountForRecursiveFiles() {
        assertThat(FileCount.countFiles("$BASE_FILE/multiDirectory")).isEqualTo(4)
    }
}