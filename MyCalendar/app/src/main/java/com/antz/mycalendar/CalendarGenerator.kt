package com.antz.mycalendar

import java.util.*

class CalendarGenerator {
    fun generateDays(month : Int, year : Int) : MutableList<Day> {
        val calendar = GregorianCalendar.getInstance(Locale.getDefault())
        val dayList = mutableListOf<Day>()
        val beforeDaysList = mutableListOf<Day>()
        val todayDate = calendar.get(Calendar.DATE)
        val todayMonth = calendar.get(Calendar.MONTH)
        val todayYear = calendar.get(Calendar.YEAR)
        val numberOfDays = getNumberOfDaysIn(month, year)

        for (i in 1..numberOfDays) {
            calendar.set(year, month, i)
            if (i == todayDate && month == todayMonth && year == todayYear)
                dayList.add(Day.from(calendar, true, true))
            else
                dayList.add(Day.from(calendar, true))
        }

        for (i in (dayList[0].dayOfWeek - 1) downTo 1) {
            calendar.set(year, month, dayList[0].date)
            calendar.add(Calendar.DAY_OF_MONTH, -i)
            beforeDaysList.add(Day.from(calendar))
        }

        for (i in 1..(7 - dayList[numberOfDays - 1].dayOfWeek)) {
            calendar.set(year, month, dayList[numberOfDays - 1].date)
            calendar.add(Calendar.DAY_OF_MONTH, i)
            dayList.add(Day.from(calendar))
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

data class Day(val dayOfWeek: Int,
               val date : Int,
               val month : Int,
               val year : Int,
               val inMonth : Boolean,
               val isToday : Boolean) {

    companion object {
        fun from(calendar : Calendar,
                 inMonth : Boolean = false,
                 isToday : Boolean = false) : Day = Day(
                calendar.get(Calendar.DAY_OF_WEEK),
                calendar.get(Calendar.DAY_OF_MONTH),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.YEAR),
                inMonth,
                isToday)
    }
}