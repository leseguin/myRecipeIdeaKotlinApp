package fr.iut.myapplication.data.persistance.converter

import androidx.room.TypeConverter


class ListConverter {

    @TypeConverter
    fun toListOfLong(flatLongList: String): ArrayList<Long> {

        val listString = flatLongList.split(",")
        val list = ArrayList<Long>()
        listString.forEach{
            list.add(it.toLong())
        }
        return list
    }


    @TypeConverter
    fun fromListOfLong(listOfLong: ArrayList<Long>): String {
        return listOfLong.joinToString(",")
    }
}