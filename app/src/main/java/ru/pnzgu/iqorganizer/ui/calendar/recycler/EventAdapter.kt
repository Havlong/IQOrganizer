package ru.pnzgu.iqorganizer.ui.calendar.recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.pnzgu.iqorganizer.R
import java.text.SimpleDateFormat
import java.util.*

class EventAdapter(private inline val onClick: (FullEvent) -> Unit) :
    ListAdapter<FullEvent, EventAdapter.EventViewHolder>(EventComparator()) {

    class EventViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nameView: TextView = itemView.findViewById(R.id.nameView)
        private val timeView: TextView = itemView.findViewById(R.id.timeView)

        fun bind(event: FullEvent) {
            nameView.text = event.name ?: "<Событие>"
            timeView.text = SimpleDateFormat("HH:mm", Locale.getDefault()).format(event.time)
        }

        companion object {
            fun create(parent: ViewGroup): EventViewHolder {
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.recyclerview_item, parent, false)
                return EventViewHolder(view)
            }
        }
    }

    class EventComparator : DiffUtil.ItemCallback<FullEvent>() {
        override fun areItemsTheSame(oldItem: FullEvent, newItem: FullEvent) = oldItem === newItem

        override fun areContentsTheSame(oldItem: FullEvent, newItem: FullEvent) = oldItem == newItem

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        return EventViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        val current = getItem(position)
        holder.itemView.setOnClickListener { onClick(current) }
        holder.bind(current)
    }
}