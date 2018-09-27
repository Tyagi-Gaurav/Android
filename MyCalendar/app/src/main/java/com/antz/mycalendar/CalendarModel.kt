package com.antz.mycalendar

import android.arch.lifecycle.ViewModel
import java.util.*

class CalendarModel : ViewModel() {
    var month = Calendar.getInstance().get(Calendar.MONTH)
    var year = Calendar.getInstance().get(Calendar.YEAR)
}