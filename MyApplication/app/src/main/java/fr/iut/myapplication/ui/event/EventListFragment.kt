package fr.iut.myapplication.ui.event
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import fr.iut.myapplication.R
import fr.iut.myapplication.data.NEW_EVENT_ID
import fr.iut.myapplication.data.persistance.database.EventDatabase
import fr.iut.myapplication.databinding.FragmentMyListEventBinding
import kotlinx.android.synthetic.main.fragment_my_list_event.*

class EventListFragment : Fragment(), EventRecyclerViewAdapter.Callbacks {


    private val eventListVM = EventListViewModel()
    private val eventListAdapter =
        EventRecyclerViewAdapter(this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentMyListEventBinding.inflate(inflater)
        binding.eventListVM = eventListVM
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        eventListVM.eventLV.observe(this, Observer {
            eventListAdapter.updateList(it)
        })
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recycler_view.adapter = eventListAdapter
        fab_add_event.setOnClickListener {
            addEvent()
        }
    }

    override fun onEventSelected(eventId: Long) {
        val bundle = bundleOf("extra_eventid" to eventId)
        findNavController().navigate(R.id.action_nav_event_to_eventFragment, bundle)
    }

    private fun addEvent(){
        val bundle = bundleOf("extra_eventid" to NEW_EVENT_ID)
        findNavController().navigate(R.id.action_nav_event_to_eventFragment, bundle)
    }
}