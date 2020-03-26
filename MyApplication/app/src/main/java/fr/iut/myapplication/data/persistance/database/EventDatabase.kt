package fr.iut.myapplication.data.persistance.database

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import fr.iut.myapplication.MainActivity
import fr.iut.myapplication.data.Event
import fr.iut.myapplication.data.Recipe
import fr.iut.myapplication.data.persistance.converter.DateToLongConverter
import fr.iut.myapplication.data.persistance.converter.ListConverter
import fr.iut.myapplication.data.persistance.dao.EventDao
import java.util.*
import kotlin.collections.ArrayList

@Database(entities = [Event::class], version = 1)
@TypeConverters(DateToLongConverter::class, ListConverter::class)
abstract class EventDatabase : RoomDatabase() {

    abstract fun eventDao(): EventDao

    companion object {
        private var application: MainActivity? = null
        @Volatile
        private var instance: EventDatabase? = null

        fun getInstance(): EventDatabase {
            if (application != null) {
                if (instance == null)
                    synchronized(this) {
                        if (instance == null)
                            instance = Room.inMemoryDatabaseBuilder(
                                application!!.applicationContext,
                                EventDatabase::class.java)
                                .allowMainThreadQueries()
                                .build()
                        firstDB()
                    }
                return instance!!
            } else
                throw RuntimeException("the database must be first initialized")
        }


        @Synchronized
        fun initialize(app: MainActivity) {
            if (application == null)
                application = app
            else
                throw RuntimeException("the database must not be initialized twice")
        }


        private fun firstDB() {
            val ev1 = Event("Anniversaire de Julie", Date().time, 10)
            ev1.addRecipeIndex(1)
            ev1.addRecipeIndex(2)
            val ev2 = Event("Paques", Date().time, 1)
            ev2.addRecipeIndex(1)
            ev2.addRecipeIndex(2)
            val ev3 = Event("nouvel an", Date().time, 100)
            ev3.addRecipeIndex(1)
            ev3.addRecipeIndex(2)
            getInstance().eventDao().apply {
                insert(ev1)
                insert(ev2)
                insert(ev3)
                insert(ev2)
                insert(ev2)
                insert(ev2)
            }
        }
    }
}