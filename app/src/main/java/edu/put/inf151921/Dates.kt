package edu.put.inf151921

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.DatePicker
import android.widget.EditText
import android.widget.TextView
import java.util.*

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
            }
        })
    }
    fun plus(view: View) {
        val date1: DatePicker = findViewById(R.id.date1) as DatePicker
        val date2: DatePicker = findViewById(R.id.date2) as DatePicker
        val number1: EditText = findViewById(R.id.number1) as EditText
        val number1Text:String = number1.text.toString()
        val daysToAdd:Int = if (number1Text.isNotEmpty()) number1Text.toInt() else 0

        val startDate: Calendar = Calendar.getInstance()
        startDate.set(date1.year, date1.month, date1.dayOfMonth)

        val endDate: Calendar = Calendar.getInstance()
        endDate.timeInMillis = startDate.timeInMillis
        endDate.add(Calendar.DATE, daysToAdd)

        date2.updateDate(endDate.get(Calendar.YEAR), endDate.get(Calendar.MONTH), endDate.get(Calendar.DAY_OF_MONTH))
    }
}