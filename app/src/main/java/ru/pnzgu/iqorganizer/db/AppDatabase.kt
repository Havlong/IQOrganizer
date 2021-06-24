package ru.pnzgu.iqorganizer.db

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ru.pnzgu.iqorganizer.db.converter.Converters
import ru.pnzgu.iqorganizer.db.dao.EventInfoDao
import ru.pnzgu.iqorganizer.db.dao.RepeatedEventDao
import ru.pnzgu.iqorganizer.db.dao.SimpleEventDao
import ru.pnzgu.iqorganizer.model.EventInfo
import ru.pnzgu.iqorganizer.model.RepeatedEvent
import ru.pnzgu.iqorganizer.model.SimpleEvent

@Database(
    entities = [EventInfo::class, RepeatedEvent::class, SimpleEvent::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun eventInfoDao(): EventInfoDao

    abstract fun repeatedEventDao(): RepeatedEventDao

    abstract fun simpleEventDao(): SimpleEventDao

    companion object {
        @Volatile
        private var instance: AppDatabase? = null

        fun getDatabase(application: Application): AppDatabase {
            return instance ?: synchronized(this) {
                val newInstance = Room.databaseBuilder(
                    application.applicationContext,
                    AppDatabase::class.java, "iq-organizer-db"
                ).build()
                instance = newInstance
                newInstance
            }
        }
    }
}