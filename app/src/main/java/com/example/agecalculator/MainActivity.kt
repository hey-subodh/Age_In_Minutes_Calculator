package com.example.agecalculator

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var btnDatePicker=findViewById<Button>(R.id.btnDatePicker)
        btnDatePicker.setOnClickListener{view->
            clickDatepicker(view)
            }
    }
    fun clickDatepicker(view:View){
        val tvSelectedDate=findViewById<TextView>(R.id.tvSelectedDate)
        val myCalender=Calendar.getInstance()
        val year=myCalender.get(Calendar.YEAR)
        val month=myCalender.get(Calendar.MONTH)
        val day = myCalender.get(Calendar.DAY_OF_MONTH)
        DatePickerDialog(this,DatePickerDialog.OnDateSetListener {
                view, year, month, dayofMonth ->
            val selectedDate="$dayofMonth/${month+1}/$year"
            tvSelectedDate.setText(selectedDate)

            val sdf=SimpleDateFormat("dd/MM/yyyy",Locale.ENGLISH)

            val theDate=sdf.parse(selectedDate)

            val selectedDateInMinute=theDate!!.time/60000
            val currentDate=sdf.parse(sdf.format(System.currentTimeMillis()))
            val currentDateInMinutes=currentDate!!.time/60000

            val difference=currentDateInMinutes-selectedDateInMinute
            val tvSelectedDateInMinutes=findViewById<TextView>(R.id.tvSelectedDateInMinutes)
            tvSelectedDateInMinutes.setText(difference.toString())
            Toast.makeText(this,"$difference Minutes", Toast.LENGTH_LONG).show()


        },year,month,day).show()

    }
}