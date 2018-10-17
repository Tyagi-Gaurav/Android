package com.antz.mycalendar

import java.io.Serializable

data class EventModel(val id : String,
                 val description : String,
                 val startDate : String,
                 val endDate : String) : Serializable