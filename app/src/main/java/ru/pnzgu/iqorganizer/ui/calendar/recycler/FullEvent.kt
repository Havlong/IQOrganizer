package ru.pnzgu.iqorganizer.ui.calendar.recycler

import android.os.Parcel
import android.os.Parcelable
import ru.pnzgu.iqorganizer.model.EventInfo
import ru.pnzgu.iqorganizer.model.SimpleEvent
import java.util.*

class FullEvent(
    var name: String?,
    var info: String?,
    var finances: Double,
    var time: Date,
    val id: Int,
    val eventId: Int
) : Parcelable {


    companion object {
        @JvmField
        val CREATOR = object : Parcelable.Creator<FullEvent?> {
            override fun createFromParcel(source: Parcel): FullEvent {
                return FullEvent(source)
            }

            override fun newArray(size: Int): Array<FullEvent?> {
                return arrayOfNulls(size)
            }

        }

        fun from(eventInfo: EventInfo, simpleEvent: SimpleEvent): FullEvent = FullEvent(
            eventInfo.name,
            eventInfo.info,
            eventInfo.finances,
            simpleEvent.time,
            simpleEvent.id,
            eventInfo.id
        )

    }

    fun toModels(): Pair<EventInfo, SimpleEvent> {
        return EventInfo(eventId, name, info, finances) to SimpleEvent(id, eventId, time)
    }

    private constructor(source: Parcel) : this(
        source.readString(),
        source.readString(),
        source.readDouble(),
        Date(source.readLong()),
        source.readInt(),
        source.readInt()
    )

    override fun describeContents(): Int = 0
    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(name)
        dest.writeString(info)
        dest.writeDouble(finances)
        dest.writeLong(time.time)
        dest.writeInt(id)
        dest.writeInt(eventId)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as FullEvent

        if (name != other.name) return false
        if (info != other.info) return false
        if (finances != other.finances) return false
        if (time != other.time) return false

        return true
    }

    override fun hashCode(): Int {
        var result = name?.hashCode() ?: 0
        result = 31 * result + (info?.hashCode() ?: 0)
        result = 31 * result + finances.hashCode()
        result = 31 * result + time.hashCode()
        return result
    }
}