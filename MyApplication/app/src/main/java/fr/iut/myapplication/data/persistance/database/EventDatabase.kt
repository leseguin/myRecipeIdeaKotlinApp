package fr.iut.myapplication.data.persistance.database

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import fr.iut.myapplication.EventApplication
import fr.iut.myapplication.MainActivity
import fr.iut.myapplication.data.Event
import fr.iut.myapplication.data.persistance.converter.LongToDateConverter
import fr.iut.myapplication.data.persistance.converter.ListConverter
import fr.iut.myapplication.data.persistance.dao.EventDao
import java.util.*

private const val DB_NAME_EVENT= "event_db.db"

@Database(entities = [Event::class], version = 1)
@TypeConverters(LongToDateConverter::class, ListConverter::class)
abstract class EventDatabase : RoomDatabase() {

    abstract fun eventDao(): EventDao

    companion object {
        private var application: Application? = null
        @Volatile
        private var instance: EventDatabase? = null

        fun getInstance(): EventDatabase {
            if (application != null) {
                if (instance == null)
                    synchronized(this) {
                        if (instance == null)
                            instance = Room.databaseBuilder(
                                application!!.applicationContext,
                                EventDatabase::class.java,
                                DB_NAME_EVENT)
                                .allowMainThreadQueries()
                                .build()
                    }
                return instance!!
            } else
                throw RuntimeException("the database must be first initialized")
        }


        @Synchronized
        fun initialize(app: EventApplication) {
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
            val ev3 = Event("nouvel an", Date().time, 1000, "je suis une super description je suis une super description " +
                    "je suis une super description je suis une super description  je suis une super description" +
                    "je suis une super description je suis une super description je suis une super description " +
                    "je suis une super description je suis une super description je suis une super description")
            ev3.addRecipeIndex(1)
            ev3.addRecipeIndex(2)
            getInstance().eventDao().apply {
                insert(ev1)
               /* insert(ev2)
                insert(ev3)
                insert(ev2)
                insert(ev2)
                insert(ev2)*/
            }
        }
    }
}