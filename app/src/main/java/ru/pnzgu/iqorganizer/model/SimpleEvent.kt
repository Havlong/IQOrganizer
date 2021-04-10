package ru.pnzgu.iqorganizer.model

import java.util.*
import kotlin.random.Random

data class SimpleEvent(
    val id: Int = Random.nextInt(),
    val event: EventInfo,
    val time: Date
)
