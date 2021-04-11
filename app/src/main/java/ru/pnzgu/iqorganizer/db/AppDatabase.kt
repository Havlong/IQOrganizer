package ru.pnzgu.iqorganizer.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ru.pnzgu.iqorganizer.db.converter.Converters
import ru.pnzgu.iqorganizer.db.dao.EventInfoDao
import ru.pnzgu.iqorganizer.db.dao.RepeatedEventDao
import ru.pnzgu.iqorganizer.db.dao.SimpleEventDao
import ru.pnzgu.iqorganizer.db.entity.EventInfo
import ru.pnzgu.iqorganizer.db.entity.RepeatedEvent
import ru.pnzgu.iqorganizer.db.entity.SimpleEvent

@Database(entities = [EventInfo::class, RepeatedEvent::class, SimpleEvent::class], version = 1)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun eventInfoDao(): EventInfoDao

    abstract fun repeatedEventDao(): RepeatedEventDao

    abstract fun simpleEventDao(): SimpleEventDao
}