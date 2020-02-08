package fr.iut.myapplication.data.persistance.dao


import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE
import fr.iut.myapplication.data.Recipe

@Dao
interface RecipeDao {

    @Query("SELECT * FROM recipe")
    fun getAll(): List<Recipe>

    @Query("SELECT * FROM recipe WHERE id = :id")
    fun findById(id: Long): Recipe

    @Insert(onConflict = REPLACE)
    fun insert(recipe: Recipe)

    @Insert
    fun insertAll(vararg recipes: Recipe)

    @Update(onConflict = REPLACE)
    fun update(recipe: Recipe)

    @Delete
    fun delete(recipe: Recipe)
}