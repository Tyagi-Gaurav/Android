package com.antz.mycalendar

import android.arch.lifecycle.ViewModelProvider
import android.graphics.Color
import android.graphics.Color.BLACK
import android.graphics.Typeface
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.LinearLayout.HORIZONTAL
import android.widget.TextView
/**
 * A simple [Fragment] subclass.
 *
 */
class MonthFragment : Fragment() {

    private val calendarGenerator: CalendarGenerator = CalendarGenerator()
    private lateinit var instance : ViewModelProvider.AndroidViewModelFactory
    private lateinit var calendarModel: CalendarModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        instance = ViewModelProvider.AndroidViewModelFactory(this.activity?.application!!)
        val viewModelProvider = ViewModelProvider(this.activity!!, instance)
        calendarModel = viewModelProvider.get(CalendarModel::class.java)

        val monthView = inflater.inflate(R.layout.fragment_month_detail, container, false)
        val monthString = resources.getStringArray(R.array.monthsList)[calendarModel.month]
        val displayString = "$monthString, ${calendarModel.year}"
        val monthTitleView = monthView.findViewById<TextView>(R.id.month_title)
        monthTitleView.text = displayString
        val monthDetailLayout = monthView.findViewById<TextView>(R.id.month_detail_layout) as LinearLayout
        val weekDays= listOf("S", "M", "T", "W", "T", "F", "S")

        val listOfDays = calendarGenerator.generateDays(calendarModel.month, calendarModel.year)

        monthDetailLayout.addView(generateWeekDayTitles(weekDays))
        val generateDaysLinearLayout = generateDaysLinearLayout(listOfDays)
        generateDaysLinearLayout.forEach{monthDetailLayout.addView(it)}

        Log.d("Calendar", "${calendarModel.month}, ${calendarModel.year}")

        return monthView
    }

    private fun generateWeekDayTitles(weekDays: List<String>) : LinearLayout {
        val linearLayout = createLinearLayout()

        for (i in 1..7) {
            linearLayout.addView(createTextView(weekDays[i-1], Color.BLUE))
        }
        return linearLayout
    }

    private fun generateDaysLinearLayout(days: List<Day>) : List<LinearLayout> {
        val linearLayoutList = mutableListOf<LinearLayout>()
        var index = 0

        val eventList = arguments?.getSerializable("eventModel") as? List<EventModel>
        eventList?.forEach {
            Log.d("MyCalendar" , "${it.description}, ${it.id}, ${it.startDate}, ${it.endDate}")
        }

        for (ll in 1..days.size / 7) {
            val linearLayout = createLinearLayout()
            for (i in 1..7) {
                val day = days[index++]
                val color = if (day.inMonth) {
                    if (isEventDay(day, eventList))
                        Color.BLUE
                    else
                        Color.BLACK
                } else Color.GRAY
                val id = (((day.year * 100) + day.month) * 100) + day.date
                linearLayout.addView(createTextView(day.date.toString(), color, day.isToday, id))
            }
            linearLayoutList.add(linearLayout)
        }

        return linearLayoutList
    }

    private fun isEventDay(day: Day, eventList: List<EventModel>?): Boolean {
        Log.d("MyCalendar", "Comparing ${day.toDateString()}")
        return eventList?.any { e -> e.startDate == day.toDateString() } ?: false
    }


    private fun createLinearLayout(): LinearLayout {
        val linearLayout = LinearLayout(this.context)
        val linearLayoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT,
                1.0F
        )
        linearLayoutParams.marginStart = 20
        linearLayout.layoutParams = linearLayoutParams
        linearLayout.orientation = HORIZONTAL
        return linearLayout
    }

    private fun createTextView(value: String,
                               color: Int = BLACK,
                               today: Boolean = false,
                               id: Int = 1) : TextView {
        val textView = TextView(this.context)
        textView.text = value
        textView.id = id
        val layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT,
                1.0F
        )
        textView.setTextColor(color)
        if (today) {
            textView.setTextColor(Color.RED)
            textView.setTypeface(null, Typeface.BOLD_ITALIC)
        }

        textView.textSize = 20.0F
        textView.layoutParams = layoutParams
        return textView
    }
}

