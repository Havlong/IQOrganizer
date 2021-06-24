package ru.pnzgu.iqorganizer.model

import java.util.*

data class Note(
    val day: Date,
    val text: String,
    val isHeld: Boolean
)
