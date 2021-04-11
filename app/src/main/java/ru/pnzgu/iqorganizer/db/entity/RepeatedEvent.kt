package ru.pnzgu.iqorganizer.db.entity

import androidx.room.Entity
import androidx.room.Fts4
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "repeated_event")
data class RepeatedEvent(
    @PrimaryKey(autoGenerate = true) val id: Int,

    val eventInfoId: Int,

    val time: Date,

    val daysBetween: Int,

    val start: Date?,

    val end: Date?
)