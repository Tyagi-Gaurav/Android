package com.antz.mycalendar

import org.assertj.core.api.Assertions.*
import org.junit.Test
import java.util.*

/**
 * Example local unit test, which will execute on the
 * development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class CalendarGeneratorTest {
    private val cgen = CalendarGenerator()

    @Test
    fun generateDaysCorrectlyForPreviousMonth() {
        //when
        val generateDays = cgen.generateDays(9, 2018)

        //then
        val day = generateDays[0]
        assertThat(day.date).isNotNull()
        assertThat(day.date).isEqualTo(30)
    }

    @Test
    fun generateDaysCorrectlyForCurrentMonth() {
        //given
        val expectedData = GregorianCalendar(2018, 3, 1)

        //when
        val generateDays = cgen.generateDays(3, 2018)

        //then
        for (i in 0..29) {
            assertThat(expectedData.get(Calendar.DATE)).isEqualTo(generateDays[i].date)
            assertThat(expectedData.get(Calendar.DAY_OF_WEEK)).isEqualTo(generateDays[i].dayOfWeek)
            expectedData.add(Calendar.DATE, 1)
        }
    }

    @Test
    fun generateDaysCorrectlyForNextMonth() {
        //given
        val expectedData = GregorianCalendar(2018, 4, 1)

        //when
        val generateDays = cgen.generateDays(3, 2018)

        //then
        for (i in 30..34) {
            assertThat(expectedData.get(Calendar.DATE)).isEqualTo(generateDays[i].date)
            assertThat(expectedData.get(Calendar.DAY_OF_WEEK)).isEqualTo(generateDays[i].dayOfWeek)
            expectedData.add(Calendar.DATE, 1)
        }
    }

    @Test
    fun checkTotalDaysInArray() {
        //when
        val generateDays = cgen.generateDays(9, 2018)

        //then
        assertThat(generateDays.size).isEqualTo(35)
    }
}
