package ru.pnzgu.iqorganizer.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "event_info")
data class EventInfo(
    @PrimaryKey(autoGenerate = true) val id: Int,

    val name: String?,

    val info: String?,

    val finances: Long
)