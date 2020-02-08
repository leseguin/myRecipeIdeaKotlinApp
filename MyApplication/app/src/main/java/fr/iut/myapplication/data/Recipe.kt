package fr.iut.myapplication.data

import androidx.room.Entity
import androidx.room.PrimaryKey

const val NEW_RECIPE_ID = 0L

@Entity(tableName = "recipe")
data class Recipe(var title: String = "",
                  var servings: Int =  0,
                  var summary: String = "",
                  @PrimaryKey(autoGenerate = true) val id: Long = NEW_RECIPE_ID)