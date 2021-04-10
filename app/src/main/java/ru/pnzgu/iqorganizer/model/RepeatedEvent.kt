package ru.pnzgu.iqorganizer.model

import java.util.*
import kotlin.random.Random

data class RepeatedEvent(
    val id: Int = Random.nextInt(),
    val eventId: Int,
    val time: Date,
    val daysBetween: Int,
    val start: Date?,
    val end: Date?
)
