package com.antz.mycalendar

import android.graphics.Color
import android.graphics.Color.BLACK
import android.graphics.Typeface
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.LinearLayout.HORIZONTAL
import android.widget.TextView
import java.util.*

/**
 * A simple [Fragment] subclass.
 *
 */
class MonthFragment : Fragment() {
    private val calendarGenerator = CalendarGenerator()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val monthView = inflater.inflate(R.layout.fragment_month, container, false)

        val month = Calendar.getInstance().get(Calendar.MONTH)
        val year = Calendar.getInstance().get(Calendar.YEAR)
        val monthString = resources.getStringArray(R.array.monthsList)[month]
        val displayString = "$monthString, $year"
        val monthTitleView = monthView.findViewById<TextView>(R.id.month_title)
        monthTitleView.text = displayString
        val monthDetailLayout = monthView.findViewById<TextView>(R.id.month_detail_layout) as LinearLayout
        val weekDays= listOf("S", "M", "T", "W", "T", "F", "S")

        val listOfDays = calendarGenerator.generateDays(month, year)

        monthDetailLayout.addView(generateWeekDayTitles(weekDays))
        val generateDaysLinearLayout = generateDaysLinearLayout(listOfDays)
        generateDaysLinearLayout.forEach{monthDetailLayout.addView(it)}

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

        for (ll in 1..days.size / 7) {
            val linearLayout = createLinearLayout()
            for (i in 1..7) {
                val day = days[index++]
                val color = if (day.inMonth) Color.BLACK else Color.GRAY
                linearLayout.addView(createTextView(day.date.toString(), color, day.isToday))
            }
            linearLayoutList.add(linearLayout)
        }

        return linearLayoutList
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

    private fun createTextView(value: String, color: Int = BLACK, today: Boolean = false) : TextView {
        val textView = TextView(this.context)
        textView.text = value
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
