package com.example.dobcalc

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private var tvSelectedDate : TextView? = null
    private var tvcalculatedMinutes : TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnDatePicker : Button = findViewById(R.id.buttonDateClicker)
        tvSelectedDate = findViewById(R.id.tvSelectedDate)
        tvcalculatedMinutes = findViewById(R.id.tvselectedMinutes)
        btnDatePicker.setOnClickListener {
            clickDatePicker()
        }

    }
    private fun clickDatePicker() {
        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)

        val dpd = DatePickerDialog(this,
                    DatePickerDialog.OnDateSetListener{ _, selectedYear, selectedMonth, selectedDayOfMonth ->

                    val selectedDate = "$selectedDayOfMonth/${selectedMonth+1}/$selectedYear"
                    tvSelectedDate?.text = selectedDate

                    val sdf = SimpleDateFormat("MM/dd/yyyy", Locale.US)
                    val theDate = sdf.parse(selectedDate)
                    theDate?.let{
                        val selectedDateInMinutes = theDate.time/ 60000
                        val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
                        currentDate?.let {
                            val currentDateInMinutes = currentDate.time/60000
                            val differenceInMinutes = currentDateInMinutes - selectedDateInMinutes
                            tvcalculatedMinutes?.text = differenceInMinutes.toString()
                        }

                       }
                     },
                    year,
                    month,
                    day)
        dpd.datePicker.maxDate = System.currentTimeMillis() - 86400000
        dpd.show()

    }
}