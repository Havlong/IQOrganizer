package ru.pnzgu.iqorganizer.db.relations

import androidx.room.Embedded
import androidx.room.Relation
import ru.pnzgu.iqorganizer.db.entity.EventInfo
import ru.pnzgu.iqorganizer.db.entity.RepeatedEvent

data class EventInfoAndRepeatedEvents(
    @Embedded val eventInfo: EventInfo,
    @Relation(
        parentColumn = "id",
        entityColumn = "eventInfoId"
    )
    val repeatedEvents: List<RepeatedEvent>
)