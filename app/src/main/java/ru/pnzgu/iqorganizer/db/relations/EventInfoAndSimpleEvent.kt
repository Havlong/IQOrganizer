package ru.pnzgu.iqorganizer.db.relations

import androidx.room.Embedded
import androidx.room.Relation
import ru.pnzgu.iqorganizer.db.entity.EventInfo
import ru.pnzgu.iqorganizer.db.entity.SimpleEvent

data class EventInfoAndSimpleEvent(
    @Embedded val eventInfo: EventInfo,
    @Relation(
        parentColumn = "id",
        entityColumn = "eventInfoId"
    )
    val simpleEvent: SimpleEvent
)