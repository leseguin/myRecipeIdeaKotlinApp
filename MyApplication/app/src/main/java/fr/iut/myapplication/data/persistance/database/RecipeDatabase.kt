package fr.iut.myapplication.data.persistance.database

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import fr.iut.myapplication.EventApplication
import fr.iut.myapplication.MainActivity
import fr.iut.myapplication.data.Recipe
import fr.iut.myapplication.data.persistance.dao.RecipeDao

private const val DB_NAME_RECIPE = "recipe_db.db"

@Database(entities = [Recipe::class], version = 1)
abstract class RecipeDatabase : RoomDatabase() {

    abstract fun recipeDao(): RecipeDao

    companion object {
        private var application: Application? = null
        @Volatile
        private var instance: RecipeDatabase? = null

        fun getInstance(): RecipeDatabase {
            if (application != null) {
                if (instance == null)
                    synchronized(this) {
                        if (instance == null)
                            instance = Room.databaseBuilder(
                                application!!.applicationContext,
                                RecipeDatabase::class.java,
                                DB_NAME_RECIPE)
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
            getInstance().recipeDao().apply {
                insert(Recipe("Boeuf Bourg2", 2, "summary of the recipe"))
                insert(Recipe("Boeuf Bourg1", 1, "summary of the recipe"))
                insert(Recipe("Boeuf Bourg3", 3, "summary of the recipe"))
            }
        }
    }
}