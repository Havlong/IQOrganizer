package ru.pnzgu.iqorganizer.db.dao

import androidx.room.*
import ru.pnzgu.iqorganizer.db.entity.SimpleEvent

@Dao
interface SimpleEventDao {
    @Insert
    fun insert(vararg simpleEvent: SimpleEvent)

    @Update
    fun update(vararg simpleEvent: SimpleEvent)

    @Delete
    fun delete(vararg simpleEvent: SimpleEvent)

    @Query("SELECT * FROM  simple_event")
    fun getAll(): List<SimpleEvent>

    @Query("SELECT * FROM simple_event WHERE id = :findId")
    fun getSimpleEventById(findId: Int): SimpleEvent
}