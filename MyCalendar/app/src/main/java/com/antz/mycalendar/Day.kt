package com.antz.mycalendar

import java.util.*

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