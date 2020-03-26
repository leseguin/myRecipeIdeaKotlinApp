package fr.iut.myapplication.data.persistance.converter

import android.annotation.SuppressLint
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class DateToStringConverter {
    @SuppressLint("SimpleDateFormat")
    fun dateConverter(date : Date?) : String {

        val formatter = SimpleDateFormat("dd/MM/YYYY")

        if (date == null){
            val newDate = Date()
            return formatter.format(newDate)
        }
        return formatter.format(date)
    }
}