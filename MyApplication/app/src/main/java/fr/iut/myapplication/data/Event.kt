package fr.iut.myapplication.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*
import kotlin.collections.ArrayList

const val NEW_EVENT_ID = 0L

@Entity(tableName = "event")
data class Event(var name: String = "",
                 var date: Long = Date().time,
                 var numberGuest: Int = 0,
                 var myIDRecipe: ArrayList<Long> = ArrayList(),
                 @PrimaryKey(autoGenerate = true) val id: Long = NEW_EVENT_ID) {

    fun addRecipeIndex(index : Long){
        myIDRecipe.add(index)
    }
}