package fr.iut.myapplication.data.persistance.converter

import androidx.room.TypeConverter
import java.util.Date

class LongToDateConverter {
    @TypeConverter
    fun fromTimestamp(timestamp: Long?) = timestamp?.let { Date(it) }

    @TypeConverter
    fun toTimestamp(date: Date?) = date?.time
}
