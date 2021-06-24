package ru.pnzgu.iqorganizer.db.dao

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import ru.pnzgu.iqorganizer.model.SimpleEvent
import java.util.*

@Dao
interface SimpleEventDao {
    @Insert
    fun insert(vararg simpleEvent: SimpleEvent)

    @Update
    fun update(vararg simpleEvent: SimpleEvent)

    @Delete
    fun delete(vararg simpleEvent: SimpleEvent)

    @Query("SELECT * FROM simple_event")
    fun getAll(): List<SimpleEvent>

    @Query("SELECT * FROM simple_event WHERE datetime(time) BETWEEN datetime(:day) AND datetime(:day, '+1 day')")
    fun getSimpleEventsForDay(day: Date): Flow<List<SimpleEvent>>

    @Query("SELECT * FROM simple_event WHERE id = :findId")
    fun getSimpleEventById(findId: Int): SimpleEvent
}