package edu.put.inf151921

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    fun showActivityHours(){
        val i = Intent(this, Hours::class.java)
        startActivity(i)
    }
    fun runClickHours(v: View){
        showActivityHours()
    }
    fun showActivityDates(){
        val i = Intent(this, Dates::class.java)
        startActivity(i)
    }
    fun runClickDates(v: View){
        showActivityDates()
    }
}