package com.antz.mycalendar


import android.graphics.Color
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

    val calendarGenerator = CalendarGenerator()

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

    fun generateWeekDayTitles(weekDays: List<String>) : LinearLayout {
        val linearLayout = LinearLayout(this.context)
        val linearLayoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT,
                1.0F
        )
        linearLayoutParams.marginStart = 20
        linearLayout.layoutParams = linearLayoutParams
        linearLayout.orientation = HORIZONTAL

        for (i in 1..7) {
            val textView = TextView(this.context)
            textView.text = weekDays[i-1]
            val layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    1.0F
            )
            textView.textSize = 20.0F
            textView.setTextColor(Color.BLACK)
            textView.layoutParams = layoutParams
            linearLayout.addView(textView)
        }
        return linearLayout
    }

    fun generateDaysLinearLayout(days: List<Day>) : List<LinearLayout> {
        val linearLayoutList = mutableListOf<LinearLayout>()
        var index = 0

        for (ll in 1..days.size / 7) {
            val linearLayout = LinearLayout(this.context)
            val linearLayoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    1.0F
            )
            linearLayoutParams.marginStart = 20
            linearLayout.layoutParams = linearLayoutParams
            linearLayout.orientation = HORIZONTAL

            for (i in 1..7) {
                val textView = TextView(this.context)
                textView.text = days[index++].date.toString()
                val layoutParams = LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        1.0F
                )
                textView.textSize = 20.0F
                textView.setTextColor(Color.BLACK)
                textView.layoutParams = layoutParams
                linearLayout.addView(textView)
            }

            linearLayoutList.add(linearLayout)
        }

        return linearLayoutList
    }
}
