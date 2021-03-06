package fr.iut.myapplication.ui.event

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import fr.iut.myapplication.R
import fr.iut.myapplication.data.Event
import fr.iut.myapplication.data.persistance.converter.DateToStringConverter
import fr.iut.myapplication.data.persistance.converter.LongToDateConverter
import kotlinx.android.synthetic.main.item_list_event.view.*


class EventRecyclerViewAdapter( private val listener: Callbacks) :
RecyclerView.Adapter<EventRecyclerViewAdapter.EventViewHolder>() {

    private var eventList = emptyList<Event>()

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) =
        holder.bind(eventList[position])

    override fun getItemCount() = eventList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        EventViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_list_event,
                parent,
                false
            ), listener
        )

    class EventViewHolder(itemView: View, listener: Callbacks) :
        RecyclerView.ViewHolder(itemView) {

        var event: Event? = null
            private set

        init {
            itemView.setOnClickListener { event?.let { listener.onEventSelected(it.id) } }
        }

        fun bind(event: Event) {
            val strConvert = DateToStringConverter()
            val convert = LongToDateConverter()
            this.event = event
            itemView.eventName.text = event.name
            itemView.eventDate.text = strConvert.dateConverter(convert.fromTimestamp(event.date))
            itemView.numberGuest.text = event.numberGuest.toString()
        }
    }

    fun updateList(eventList: List<Event>) {
        this.eventList = eventList
        notifyDataSetChanged()
    }

    interface Callbacks {
        fun onEventSelected(eventId: Long)
    }
}