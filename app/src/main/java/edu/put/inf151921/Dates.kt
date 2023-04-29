package edu.put.inf151921

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText

class Dates : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dates)
    }
    fun plus(v: View){
        val n: EditText = findViewById(R.id.number1) as EditText
        val nText: String = n.text.toString()
        var nValue: Int = if (nText.isNotEmpty()) nText.toInt() else 0
        var newValue:Int  = nValue +1
        n.setText(newValue.toString())
    }
}