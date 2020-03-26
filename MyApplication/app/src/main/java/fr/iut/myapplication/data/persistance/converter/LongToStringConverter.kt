package fr.iut.myapplication.data.persistance.converter

import android.annotation.SuppressLint
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

object LongToStringConverter {
    @SuppressLint("SimpleDateFormat")
    @JvmStatic
    fun convert(date : Long) : String {
        val formatter = SimpleDateFormat("dd/MM/yyyy")
        return formatter.format(Date(date))
    }
}