package ru.pnzgu.iqorganizer

import android.app.Application
import ru.pnzgu.iqorganizer.db.AppDatabase

class IQOrganizerApplication : Application() {
    val database by lazy { AppDatabase.getDatabase(this) }
}