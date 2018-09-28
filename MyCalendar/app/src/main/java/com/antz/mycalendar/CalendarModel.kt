package com.antz.mycalendar

import android.arch.lifecycle.ViewModel
import java.util.*

class CalendarModel : ViewModel() {
    var month = Calendar.getInstance().get(Calendar.MONTH)
    var year = Calendar.getInstance().get(Calendar.YEAR)

    override fun toString(): String {
        return "Month: $month, Year: $year"
    }

    fun getNextMonthAndYear(month: Int, year : Int) : Pair<Int, Int> {
        val calendar = GregorianCalendar.getInstance()
        calendar.set(year, month, 1)
        calendar.add(Calendar.MONTH, 1)
        return Pair(calendar.get(Calendar.MONTH), calendar.get(Calendar.YEAR))
    }

    fun getPreviousMonthAndYear(month: Int, year : Int) : Pair<Int, Int> {
        val calendar = GregorianCalendar.getInstance()
        calendar.set(year, month, 1)
        calendar.add(Calendar.MONTH, -1)
        return Pair(calendar.get(Calendar.MONTH), calendar.get(Calendar.YEAR))
    }

    fun incrementMonthAndYear() {
        val nextMonthAndYear = getNextMonthAndYear(month, year)
        month = nextMonthAndYear.first
        year = nextMonthAndYear.second
    }

    fun decrementMonthAndYear() {
        val nextMonthAndYear = getPreviousMonthAndYear(month, year)
        month = nextMonthAndYear.first
        year = nextMonthAndYear.second
    }
}