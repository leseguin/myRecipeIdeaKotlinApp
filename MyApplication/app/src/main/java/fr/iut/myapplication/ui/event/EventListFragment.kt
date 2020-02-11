package fr.iut.myapplication.ui.event
import android.os.Bundle
import android.view.*
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import fr.iut.myapplication.R
import fr.iut.myapplication.data.persistance.database.EventDatabase
import kotlinx.android.synthetic.main.fragment_my_list_recipe.*
import kotlinx.android.synthetic.main.fragment_my_list_recipe.view.*
import kotlinx.android.synthetic.main.fragment_my_list_recipe.view.group_empty_view

class EventListFragment : Fragment(), EventRecyclerViewAdapter.Callbacks {

    private var eventList = EventDatabase.getInstance().eventDao().getAll()
    private val eventListAdapter =
        EventRecyclerViewAdapter(eventList, this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_my_list_event, container, false)
        view.recycler_view.adapter = eventListAdapter
        view.group_empty_view.visibility = if (eventList.isEmpty()) View.VISIBLE else View.GONE

        return view
    }

    override fun onResume() {
        super.onResume()
        updateList()
    }

    private fun updateList() {
        eventListAdapter.updateList(EventDatabase.getInstance().eventDao().getAll())
        group_empty_view.visibility = if (eventList.isEmpty()) View.VISIBLE else View.GONE
    }

    override fun onEventSelected(eventId: Long) {
        val bundle = bundleOf("extra_eventid" to eventId)
        findNavController().navigate(R.id.action_nav_event_to_eventFragment, bundle)

    }


}