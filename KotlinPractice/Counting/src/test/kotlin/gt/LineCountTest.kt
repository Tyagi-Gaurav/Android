package gt

import org.assertj.core.api.Assertions
import org.junit.Test

class LineCountTest {
    val BASE_FILE = "Counting/src/test/resources"

    @Test
    fun shouldReturnZeroOnEmptyDirectory() {
        Assertions.assertThat(LineCount.countFiles("$BASE_FILE/empty")).isEqualTo(0)
    }

    @Test
    fun shouldCountLinesForDirectoryWithSingleFile() {
        Assertions.assertThat(LineCount.countFiles("$BASE_FILE/oneFile")).isEqualTo(2)
    }

    @Test
    fun shouldCountLinesForDirectoryWithTwoFiles() {
        Assertions.assertThat(LineCount.countFiles("$BASE_FILE/twoFiles")).isEqualTo(11)
    }

    @Test
    fun shouldCountForRecursiveFiles() {
        Assertions.assertThat(LineCount.countFiles("$BASE_FILE/multiDirectory")).isEqualTo(12)
    }
}