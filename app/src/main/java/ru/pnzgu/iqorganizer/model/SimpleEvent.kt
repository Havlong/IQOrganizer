package ru.pnzgu.iqorganizer.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "simple_event")
data class SimpleEvent(
    @PrimaryKey(autoGenerate = true) val id: Int,

    val eventInfoId: Int,

    val time: Date
)