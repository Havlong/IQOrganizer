package ru.pnzgu.iqorganizer.db.dao

import androidx.room.*
import ru.pnzgu.iqorganizer.db.entity.EventInfo
import ru.pnzgu.iqorganizer.db.relations.EventInfoAndRepeatedEvents
import ru.pnzgu.iqorganizer.db.relations.EventInfoAndSimpleEvent

@Dao
interface EventInfoDao {
    @Insert
    fun insert(vararg eventInfo: EventInfo)

    @Update
    fun update(vararg eventInfo: EventInfo)

    @Delete
    fun delete(vararg eventInfo: EventInfo)

    @Query("SELECT * FROM event_info")
    fun getAll(): List<EventInfo>

    @Query("SELECT * FROM event_info WHERE id = :findId")
    fun getEventInfoById(findId: Int): EventInfo

    @Transaction
    @Query("SELECT * FROM event_info")
    fun getEventInfoAndSimpleEvents(): List<EventInfoAndSimpleEvent>

    @Transaction
    @Query("SELECT * FROM event_info")
    fun getEventInfoAndRepeatedEvents(): List<EventInfoAndRepeatedEvents>
}
