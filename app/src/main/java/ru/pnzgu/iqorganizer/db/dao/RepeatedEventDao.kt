package ru.pnzgu.iqorganizer.db.dao

import androidx.room.*
import ru.pnzgu.iqorganizer.db.entity.RepeatedEvent

@Dao
interface RepeatedEventDao {
    @Insert
    fun insert(vararg repeatedEvent: RepeatedEvent)

    @Update
    fun update(vararg repeatedEvent: RepeatedEvent)

    @Delete
    fun delete(vararg repeatedEvent: RepeatedEvent)

    @Query("SELECT * FROM repeated_event")
    fun getAll(): List<RepeatedEvent>

    @Query("SELECT * FROM repeated_event WHERE id = :findId")
    fun getRepeatedEventById(findId: Int): RepeatedEvent
}