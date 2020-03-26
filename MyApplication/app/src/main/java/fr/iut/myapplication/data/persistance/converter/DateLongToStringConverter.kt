package fr.iut.myapplication.data.persistance.converter

import java.text.DateFormat
import java.util.*

class DateLongToStringConverter {
    fun dateConverter(date : Date?) : String {
        val shortDateFormat = DateFormat.getDateTimeInstance(
            DateFormat.SHORT,
            DateFormat.SHORT
        )
        if (date == null){
            val newDate = Date()
            return shortDateFormat.format(newDate)
        }
        return shortDateFormat.format(date)
    }
}