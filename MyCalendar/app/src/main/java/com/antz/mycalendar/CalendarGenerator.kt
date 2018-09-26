package com.antz.mycalendar

import java.util.*

class CalendarGenerator {
    fun generateDays(month : Int, year : Int) : MutableList<Day> {
        val numberOfDays = getNumberOfDaysIn(month, year)
        val calendar = GregorianCalendar.getInstance(Locale.getDefault())
        val dayList = mutableListOf<Day>()
        val beforeDaysList = mutableListOf<Day>()

        for (i in 1..numberOfDays) {
            calendar.set(year, month, i)
            dayList.add(Day(calendar.get(Calendar.DAY_OF_WEEK), i, true))
        }

        for (i in (dayList[0].dayOfWeek - 1) downTo 1) {
            calendar.set(year, month, dayList[0].date)
            calendar.add(Calendar.DAY_OF_MONTH, -i)
            beforeDaysList.add(Day(calendar.get(Calendar.DAY_OF_WEEK),
                    calendar.get(Calendar.DAY_OF_MONTH)))
        }

        for (i in 1..(7 - dayList[numberOfDays - 1].dayOfWeek)) {
            calendar.set(year, month, dayList[numberOfDays - 1].date)
            calendar.add(Calendar.DAY_OF_MONTH, i)
            dayList.add(Day(calendar.get(Calendar.DAY_OF_WEEK),
                    calendar.get(Calendar.DAY_OF_MONTH)))
        }

        beforeDaysList.addAll(dayList)
        return beforeDaysList
    }

    private fun getNumberOfDaysIn(month: Int, year : Int) : Int {
        val instance = GregorianCalendar.getInstance()
        instance.set(year, month, 1)
        return instance.getActualMaximum(Calendar.DAY_OF_MONTH)
    }
}

data class Day(val dayOfWeek: Int, val date : Int, val inMonth : Boolean = false)