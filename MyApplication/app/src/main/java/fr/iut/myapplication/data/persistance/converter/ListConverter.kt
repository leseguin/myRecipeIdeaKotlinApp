package fr.iut.myapplication.data.persistance.converter

import androidx.room.TypeConverter


class ListConverter {

    @TypeConverter
    fun toListOfLong(flatLongList: String): ArrayList<Long> {
        val list = ArrayList<Long>()
        if(flatLongList == "") return list
        val listString = flatLongList.split(",")

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