package ru.pnzgu.iqorganizer.ui.calendar

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.pnzgu.iqorganizer.db.AppDatabase

class CalendarViewModelFactory(val db: AppDatabase) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(CalendarViewModel::class.java))
            modelClass.getConstructor(AppDatabase::class.java)
                .newInstance(db)
        else
            modelClass.newInstance()
    }

}