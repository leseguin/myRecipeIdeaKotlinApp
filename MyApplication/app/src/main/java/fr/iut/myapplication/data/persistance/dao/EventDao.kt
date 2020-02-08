package fr.iut.myapplication.data.persistance.dao

import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE
import fr.iut.myapplication.data.Event
import fr.iut.myapplication.data.Recipe

@Dao
interface EventDao {

    @Query("SELECT * FROM event")
    fun getAll(): List<Event>

    @Query("SELECT * FROM event WHERE id = :id")
    fun findById(id: Long): Event

    @Insert(onConflict = REPLACE)
    fun insert(event: Event)

    @Insert
    fun insertAll(vararg event: Event)

    @Update(onConflict = REPLACE)
    fun update(event: Event)

    @Delete
    fun delete(event: Event)
}