package ru.pnzgu.iqorganizer.model

import kotlin.random.Random

data class EventInfo(
    val id: Int = Random.nextInt(),
    val name: String?,
    val info: String?,
    val finances: Long
)
