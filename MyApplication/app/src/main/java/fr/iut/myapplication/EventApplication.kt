package fr.iut.myapplication

import android.app.Application
import fr.iut.myapplication.data.persistance.database.EventDatabase
import fr.iut.myapplication.data.persistance.database.RecipeDatabase

class EventApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        RecipeDatabase.initialize(this)
        EventDatabase.initialize(this)
    }
}