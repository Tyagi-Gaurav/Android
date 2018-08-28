package serializer

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class SerializerTest {
    @Test
    fun simpleTest() {
        val result = serialize(SingleStringProp("x"))
        assertThat(result).isEqualTo("""{"s": "x"}""")
    }
}