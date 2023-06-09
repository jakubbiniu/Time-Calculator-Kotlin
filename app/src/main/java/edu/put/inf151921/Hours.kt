package edu.put.inf151921

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText

class Hours : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hours)

    }
    fun Add(v: View){
        val hours1: EditText = findViewById(R.id.Hours1) as EditText
        val hours1Text: String = hours1.text.toString()
        val currentHours1Value: Int = if (hours1Text.isNotEmpty()) hours1Text.toInt() else 0
        val hours2: EditText = findViewById(R.id.Hours2) as EditText
        val hours2Text: String = hours2.text.toString()
        val currentHours2Value: Int = if (hours2Text.isNotEmpty()) hours2Text.toInt() else 0
        val minutes1: EditText = findViewById(R.id.Minutes1) as EditText
        val minutes1Text: String = minutes1.text.toString()
        val currentMinutes1Value: Int = if (minutes1Text.isNotEmpty()) minutes1Text.toInt() else 0
        val minutes2: EditText = findViewById(R.id.Minutes2) as EditText
        val minutes2Text: String = minutes2.text.toString()
        val currentMinutes2Value: Int = if (minutes2Text.isNotEmpty()) minutes2Text.toInt() else 0
        val seconds1: EditText = findViewById(R.id.Seconds1) as EditText
        val seconds1Text: String = seconds1.text.toString()
        val currentSeconds1Value: Int = if (seconds1Text.isNotEmpty()) seconds1Text.toInt() else 0
        val seconds2: EditText = findViewById(R.id.Seconds2) as EditText
        val seconds2Text: String = seconds2.text.toString()
        val currentSeconds2Value: Int = if (seconds2Text.isNotEmpty()) seconds2Text.toInt() else 0

        val newSeconds1Value: Int = (currentSeconds1Value + currentSeconds2Value)%60
        val addToMinutes: Int = (currentSeconds1Value + currentSeconds2Value)/60
        seconds1.setText(newSeconds1Value.toString())
        val newMinutes1Value: Int = (currentMinutes1Value + currentMinutes2Value + addToMinutes)%60
        val addToHours: Int = (currentMinutes1Value + currentMinutes2Value + addToMinutes)/60
        minutes1.setText(newMinutes1Value.toString())
        val newHours1Value: Int = currentHours1Value + currentHours2Value + addToHours
        hours1.setText(newHours1Value.toString())

        hours2.setText("0")
        minutes2.setText("0")
        seconds2.setText("0")
    }

    fun Substract(v: View){
        val hours1: EditText = findViewById(R.id.Hours1) as EditText
        val hours1Text: String = hours1.text.toString()
        var currentHours1Value: Int = if (hours1Text.isNotEmpty()) hours1Text.toInt() else 0
        val hours2: EditText = findViewById(R.id.Hours2) as EditText
        val hours2Text: String = hours2.text.toString()
        var currentHours2Value: Int = if (hours2Text.isNotEmpty()) hours2Text.toInt() else 0
        val minutes1: EditText = findViewById(R.id.Minutes1) as EditText
        val minutes1Text: String = minutes1.text.toString()
        var currentMinutes1Value: Int = if (minutes1Text.isNotEmpty()) minutes1Text.toInt() else 0
        val minutes2: EditText = findViewById(R.id.Minutes2) as EditText
        val minutes2Text: String = minutes2.text.toString()
        var currentMinutes2Value: Int = if (minutes2Text.isNotEmpty()) minutes2Text.toInt() else 0
        val seconds1: EditText = findViewById(R.id.Seconds1) as EditText
        val seconds1Text: String = seconds1.text.toString()
        var currentSeconds1Value: Int = if (seconds1Text.isNotEmpty()) seconds1Text.toInt() else 0
        val seconds2: EditText = findViewById(R.id.Seconds2) as EditText
        val seconds2Text: String = seconds2.text.toString()
        var currentSeconds2Value: Int = if (seconds2Text.isNotEmpty()) seconds2Text.toInt() else 0

        var minutes1ToAdd: Int = currentSeconds1Value/60
        currentSeconds1Value = currentSeconds1Value%60
        currentMinutes1Value += minutes1ToAdd
        var Hours1ToAdd: Int = currentMinutes1Value/60
        currentMinutes1Value = currentMinutes1Value%60
        currentHours1Value += Hours1ToAdd
        var minutes2ToAdd: Int = currentSeconds2Value/60
        currentSeconds2Value = currentSeconds2Value%60
        currentMinutes2Value += minutes2ToAdd
        var Hours2ToAdd: Int = currentMinutes2Value/60
        currentMinutes2Value = currentMinutes2Value%60
        currentHours2Value += Hours2ToAdd

        var swap:Boolean=false
        if(currentHours2Value>currentHours1Value){
            swap = true
        }
        else if(currentHours2Value==currentHours1Value && currentMinutes2Value>currentMinutes1Value){
            swap = true
        }
        else if(currentHours2Value==currentHours1Value && currentMinutes2Value==currentMinutes1Value && currentSeconds2Value>currentSeconds1Value){
            swap = true
        }

        if (swap){
            var temp = currentHours1Value
            currentHours1Value = currentHours2Value
            currentHours2Value = temp
            temp = currentMinutes1Value
            currentMinutes1Value = currentMinutes2Value
            currentMinutes2Value = temp
            temp = currentSeconds1Value
            currentSeconds1Value = currentSeconds2Value
            currentSeconds2Value = temp
        }


        var carry: Int = 0
        var newSeconds1Value = 0
        if(currentSeconds1Value<currentSeconds2Value){
            newSeconds1Value = currentSeconds1Value + 60 - currentSeconds2Value
            carry = -1
        }
        else{
            newSeconds1Value = currentSeconds1Value - currentSeconds2Value
        }
        var newMinutes1Value = currentMinutes1Value - currentMinutes2Value + carry
        if (newMinutes1Value<0){
            newMinutes1Value += 60
            carry = -1
        }
        else{
            carry=0
        }
        var newHours1Value = currentHours1Value - currentHours2Value + carry

        if(swap){
            if(newHours1Value>0){
                newHours1Value*=-1
            }
            else if(newMinutes1Value>0){
                newMinutes1Value*=-1
            }
            else{
                newSeconds1Value*=-1
            }
        }


        seconds1.setText(newSeconds1Value.toString())
        minutes1.setText(newMinutes1Value.toString())
        hours1.setText(newHours1Value.toString())
        hours2.setText("0")
        minutes2.setText("0")
        seconds2.setText("0")
    }
    fun AC(v: View){
        val hours1: EditText = findViewById(R.id.Hours1) as EditText
        hours1.setText("0")
        val hours2: EditText = findViewById(R.id.Hours2) as EditText
        hours2.setText("0")
        val minutes1: EditText = findViewById(R.id.Minutes1) as EditText
        minutes1.setText("0")
        val minutes2: EditText = findViewById(R.id.Minutes2) as EditText
        minutes2.setText("0")
        val seconds1: EditText = findViewById(R.id.Seconds1) as EditText
        seconds1.setText("0")
        val seconds2: EditText = findViewById(R.id.Seconds2) as EditText
        seconds2.setText("0")
    }
}