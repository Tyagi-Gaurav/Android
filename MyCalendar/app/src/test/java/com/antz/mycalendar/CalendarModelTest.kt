package com.antz.mycalendar

import org.assertj.core.api.Assertions.assertThat
import org.junit.experimental.theories.DataPoints
import org.junit.experimental.theories.Theories
import org.junit.experimental.theories.Theory
import org.junit.runner.RunWith
import kotlin.reflect.KFunction1


@RunWith(Theories::class)
class CalendarModelTest {

    @Theory
    fun shouldIncrementMonthAndYearCorrectly(fixture : Fixture) {
        //given
        val calendarModel = CalendarModel()
        calendarModel.month = fixture.month
        calendarModel.year = fixture.year

        //when
        fixture.function.invoke(calendarModel)

        //then
        assertThat(calendarModel.month).isEqualTo(fixture.expectedMonth)
        assertThat(calendarModel.year).isEqualTo(fixture.expectedYear)
    }

    companion object {
        @DataPoints
        @JvmField
        val FIXTURE = arrayOf(
                Fixture(4, 2018, 5, 2018, CalendarModel::incrementMonthAndYear),
                Fixture(11, 2018, 0, 2019, CalendarModel::incrementMonthAndYear),
                Fixture(0, 2019, 11, 2018, CalendarModel::decrementMonthAndYear),
                Fixture(8, 2019, 7, 2019, CalendarModel::decrementMonthAndYear)
        )
    }

    data class Fixture(val month: Int, val year: Int, val expectedMonth: Int, val expectedYear: Int,
                       val function: KFunction1<CalendarModel, Unit>)
}