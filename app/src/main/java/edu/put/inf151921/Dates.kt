package edu.put.inf151921

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.DatePicker
import android.widget.EditText
import android.widget.TextView
import java.util.*
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.Month
import kotlin.io.path.createTempDirectory


class Dates : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dates)
        val date1: DatePicker = findViewById(R.id.date1)
        val date2: DatePicker = findViewById(R.id.date2)
        val result: EditText = findViewById(R.id.number1) as EditText
        val days: TextView = findViewById(R.id.text4)
        date2.init(date2.year, date2.month, date2.dayOfMonth, object : DatePicker.OnDateChangedListener {
            override fun onDateChanged(view: DatePicker, year: Int, monthOfYear: Int, dayOfMonth: Int) {
                val startDate: Calendar = Calendar.getInstance()
                startDate.set(date1.year, date1.month, date1.dayOfMonth)
                val endDate: Calendar = Calendar.getInstance()
                endDate.set(year, monthOfYear, dayOfMonth)
                val diffInMillis: Long = endDate.timeInMillis - startDate.timeInMillis
                val diffInDays: Int = (diffInMillis / (24 * 60 * 60 * 1000)).toInt()
                result.setText(diffInDays.toString())
                days.text = countWorkingDays(startDate,endDate).toString()
            }
        })
        date1.init(date1.year, date1.month, date1.dayOfMonth, object : DatePicker.OnDateChangedListener {
            override fun onDateChanged(view: DatePicker, year: Int, monthOfYear: Int, dayOfMonth: Int) {
                val startDate: Calendar = Calendar.getInstance()
                startDate.set(date1.year, date1.month, date1.dayOfMonth)
                val endDate: Calendar = Calendar.getInstance()
                endDate.set(date2.year, date2.month, date2.dayOfMonth)
                val diffInMillis: Long = endDate.timeInMillis - startDate.timeInMillis
                val diffInDays: Int = (diffInMillis / (24 * 60 * 60 * 1000)).toInt()
                result.setText(diffInDays.toString())
                days.text = countWorkingDays(startDate,endDate).toString()
            }
        })
    }
    fun plus(view: View) {
        val date1: DatePicker = findViewById(R.id.date1) as DatePicker
        val date2: DatePicker = findViewById(R.id.date2) as DatePicker
        val number1: EditText = findViewById(R.id.number1) as EditText
        val days: TextView = findViewById(R.id.text4)
        val number1Text:String = number1.text.toString()
        val daysToAdd:Int = if (number1Text.isNotEmpty()) number1Text.toInt() else 0

        val startDate: Calendar = Calendar.getInstance()
        startDate.set(date1.year, date1.month, date1.dayOfMonth)

        val endDate: Calendar = Calendar.getInstance()
        endDate.timeInMillis = startDate.timeInMillis
        endDate.add(Calendar.DATE, daysToAdd)

        date2.updateDate(endDate.get(Calendar.YEAR), endDate.get(Calendar.MONTH), endDate.get(Calendar.DAY_OF_MONTH))
        days.text = countWorkingDays(startDate,endDate).toString()
    }
    fun getEaster(year: Int): Calendar {
        val a = year % 19
        val b = year / 100
        val c = year % 100
        val d = b / 4
        val e = b % 4
        val f = (b + 8) / 25
        val g = (b - f + 1) / 3
        val h = (19 * a + b - d - g + 15) % 30
        val i = c / 4
        val k = c % 4
        val l = (32 + 2 * e + 2 * i - h - k) % 7
        val m = (a + 11 * h + 22 * l) / 451
        val n = h + l - 7 * m + 114
        val month = n / 31
        val day = n % 31 + 1
        val easterSunday = Calendar.getInstance()
        easterSunday.set(year, month - 1, day)
        return easterSunday
    }
    fun getEasterMonday(year: Int): Calendar {
        val easterSunday = getEaster(year)
        val easterMonday = Calendar.getInstance()
        easterMonday.time = easterSunday.time
        easterMonday.add(Calendar.DATE, 1)
        return easterMonday
    }

    fun getCorpusChristi(year: Int): Calendar {
        val easterSunday = getEaster(year)
        val corpusChristi = Calendar.getInstance()
        corpusChristi.time = easterSunday.time
        corpusChristi.add(Calendar.DATE, 60)
        return corpusChristi
    }

    fun countWorkingDays(start: Calendar, end: Calendar): Int {
        if(start>end){
            return countWorkingDays(end,start)*(-1)
        }
        var workingDays = 0
        var currentDate = start.clone() as Calendar
        currentDate.add(Calendar.DAY_OF_MONTH, 1)

        while (currentDate <= end) {
            val dayOfWeek = currentDate.get(Calendar.DAY_OF_WEEK)
            val isHoliday = (currentDate.get(Calendar.MONTH) == Calendar.JANUARY && currentDate.get(Calendar.DAY_OF_MONTH) == 1) ||
                    (currentDate.get(Calendar.MONTH) == Calendar.JANUARY && currentDate.get(Calendar.DAY_OF_MONTH) == 6) ||
                    (currentDate.get(Calendar.MONTH) == Calendar.MAY && currentDate.get(Calendar.DAY_OF_MONTH) == 1) ||
                    (currentDate.get(Calendar.MONTH) == Calendar.MAY && currentDate.get(Calendar.DAY_OF_MONTH) == 3) ||
                    (currentDate.get(Calendar.MONTH) == Calendar.AUGUST && currentDate.get(Calendar.DAY_OF_MONTH) == 15) ||
                    (currentDate.get(Calendar.MONTH) == Calendar.NOVEMBER && currentDate.get(Calendar.DAY_OF_MONTH) == 1) ||
                    (currentDate.get(Calendar.MONTH) == Calendar.NOVEMBER && currentDate.get(Calendar.DAY_OF_MONTH) == 11) ||
                    (currentDate.get(Calendar.MONTH) == Calendar.DECEMBER && currentDate.get(Calendar.DAY_OF_MONTH) == 25) ||
                    (currentDate.get(Calendar.MONTH) == Calendar.DECEMBER && currentDate.get(Calendar.DAY_OF_MONTH) == 26) ||
                    (currentDate.get(Calendar.MONTH) == getEasterMonday(currentDate.get(Calendar.YEAR)).get(Calendar.MONTH) &&
                            currentDate.get(Calendar.DAY_OF_MONTH) == getEasterMonday(currentDate.get(Calendar.YEAR)).get(Calendar.DAY_OF_MONTH)) ||
                    (currentDate.get(Calendar.MONTH) == getCorpusChristi(currentDate.get(Calendar.YEAR)).get(Calendar.MONTH) &&
                            currentDate.get(Calendar.DAY_OF_MONTH) == getCorpusChristi(currentDate.get(Calendar.YEAR)).get(Calendar.DAY_OF_MONTH))


            if (dayOfWeek != Calendar.SATURDAY && dayOfWeek != Calendar.SUNDAY && !isHoliday) {
                workingDays++
            }
            currentDate.add(Calendar.DAY_OF_MONTH, 1)
        }
        return workingDays
    }

}