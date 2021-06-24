package ru.pnzgu.iqorganizer.ui.calendar

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CalendarView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.pnzgu.iqorganizer.IQOrganizerApplication
import ru.pnzgu.iqorganizer.R
import ru.pnzgu.iqorganizer.ui.calendar.recycler.EventAdapter
import ru.pnzgu.iqorganizer.ui.calendar.recycler.FullEvent
import ru.pnzgu.iqorganizer.ui.dialogs.ShowEventDialog
import java.util.*

class CalendarFragment : Fragment() {

    private val db = (requireActivity().application as IQOrganizerApplication).database

    private val calendarViewModel: CalendarViewModel by viewModels {
        CalendarViewModelFactory(db)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_calendar, container, false)
        val textView: TextView = root.findViewById(R.id.text_calendar)
        val calendarView: CalendarView = root.findViewById(R.id.calendarView)
        val recyclerView: RecyclerView = root.findViewById(R.id.recyclerView)

        val adapter = EventAdapter { event: FullEvent ->
            val dialog = ShowEventDialog.newInstance(event)
            childFragmentManager.setFragmentResultListener(
                "show_event_result",
                viewLifecycleOwner
            ) { key, bundle ->
                if (key == "show_event_result") {
                    val updatedEvent: FullEvent = bundle.getParcelable("event")!!
                    val (masterEvent, simpleEvent) = updatedEvent.toModels()
                    db.simpleEventDao().update(simpleEvent)
                    db.eventInfoDao().update(masterEvent)
                }
            }
            dialog.show(childFragmentManager, ShowEventDialog.TAG)
        }

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(context)

        calendarView.setOnDateChangeListener { view, _, _, _ ->
            calendarViewModel.date.value = Date(view.date)
        }

        calendarViewModel.text.observe(viewLifecycleOwner, {
            textView.text = it
        })

        calendarViewModel.fullEvents.observe(viewLifecycleOwner, {
            adapter.submitList(it)
        })

        return root
    }
}