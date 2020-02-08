package fr.iut.myapplication.ui.event

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import fr.iut.myapplication.R
import fr.iut.myapplication.data.Event
import fr.iut.myapplication.data.Recipe
import fr.iut.myapplication.data.connexion.Connexion
import kotlinx.android.synthetic.main.item_list_event.view.*
import kotlinx.android.synthetic.main.item_list_recipe.view.*

class EventRecyclerViewAdapter(private var eventList: List<Event>, private val listener: Callbacks) :
RecyclerView.Adapter<EventRecyclerViewAdapter.EventViewHolder>() {


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
            this.event = event
            itemView.eventName.text = event.name
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