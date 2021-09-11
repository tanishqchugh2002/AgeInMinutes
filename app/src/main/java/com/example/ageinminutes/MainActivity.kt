package com.example.ageinminutes

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

//    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        binding= ActivityMainBinding.inflate(LayoutInflater)
        setContentView(R.layout.activity_main)

        val datePicker = findViewById<Button>(R.id.btnDatePicker)
        datePicker.setOnClickListener { view ->
            clickDatePicker(view)


        }
    }

    fun clickDatePicker(view:View){
        val myCalender = Calendar.getInstance()
        val year = myCalender.get(Calendar.YEAR)
        val month = myCalender.get(Calendar.MONTH)
        val day = myCalender.get(Calendar.DAY_OF_MONTH)

        val dpd=DatePickerDialog(this,DatePickerDialog.OnDateSetListener{
                View,selectedYear,selectedMonth,selectedDayOfMonth ->

                val selecteDate = "$selectedDayOfMonth/${selectedMonth+1}/$selectedYear"

            val tvSelectedDate = findViewById<TextView>(R.id.SelectedDate)

            tvSelectedDate.setText(selecteDate)

            val sdf = SimpleDateFormat("dd/MM/yyyy")
             val theDate= sdf.parse(selecteDate)
            val selectedDateInMinutes = theDate!!.time/60000

            val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))

            val currentDateToMinutes = currentDate!!.time/60000

            val differentInMinutes = currentDateToMinutes - selectedDateInMinutes

            val AgeInMinutes = findViewById<TextView>(R.id.AgeInMinutes)

            AgeInMinutes.setText(differentInMinutes.toString())
        }
            ,
            year
            ,month
            ,day)
        dpd.datePicker.setMaxDate(Date().time-86400000)
        dpd.show()

    }

}