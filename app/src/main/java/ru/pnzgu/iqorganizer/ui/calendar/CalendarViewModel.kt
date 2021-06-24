package ru.pnzgu.iqorganizer.ui.calendar


import androidx.lifecycle.*
import ru.pnzgu.iqorganizer.db.AppDatabase
import ru.pnzgu.iqorganizer.ui.calendar.recycler.FullEvent
import java.util.*

class CalendarViewModel(private val database: AppDatabase) : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Ваш органайзер"
    }

    val date = MutableLiveData<Date>().apply {
        value = Date()
    }

    val text: LiveData<String> = _text

    private val simpleEvents = Transformations.switchMap(date) { day ->
        database.simpleEventDao().getSimpleEventsForDay(day).asLiveData()
    }

    val fullEvents = Transformations.map(simpleEvents) {
        it.map { event ->
            FullEvent.from(
                database.eventInfoDao().getEventInfoById(event.eventInfoId),
                event
            )
        }
    }
}
