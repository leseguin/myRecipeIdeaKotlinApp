package fr.iut.myapplication.ui.event

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import fr.iut.myapplication.R
import fr.iut.myapplication.data.Event
import fr.iut.myapplication.data.NEW_EVENT_ID
import fr.iut.myapplication.data.NEW_RECIPE_ID
import fr.iut.myapplication.data.persistance.database.EventDatabase
import fr.iut.myapplication.databinding.FragmentEventBinding
import kotlinx.android.synthetic.main.fragment_event.*
import android.widget.CalendarView
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingListener
import fr.iut.myapplication.data.persistance.converter.DateLongToStringConverter
import fr.iut.myapplication.data.persistance.converter.DateToLongConverter
import java.util.*


class EventFragment : Fragment() {

    companion object {
        private const val EXTRA_EVENT_ID = "extra_eventid"


        fun newInstance(eventId: Long) = EventFragment().apply {
            arguments = bundleOf(EXTRA_EVENT_ID to eventId)
        }

    }

    //private lateinit var event : Event
    private var eventId: Long = NEW_EVENT_ID
    private lateinit var eventVM: EventViewModel


    @Suppress("UNCHECKED_CAST")
    inline fun <VM : ViewModel> viewModelFactory(crossinline f: () -> VM) = object : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T = f() as T
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        eventId = savedInstanceState?.getLong(EXTRA_EVENT_ID) ?: arguments?.getLong(EXTRA_EVENT_ID) ?: NEW_EVENT_ID

        /*
        event = if (eventId == NEW_EVENT_ID) {
            activity?.setTitle(R.string.add_recipe_title)
            Event()
        } else {
            EventDatabase.getInstance().eventDao().findById(eventId)
        }
        setHasOptionsMenu(true)
        */
        eventVM = ViewModelProvider(this, viewModelFactory { EventViewModel(eventId) }).get()


    }



    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putLong(EXTRA_EVENT_ID, eventId)
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding = FragmentEventBinding.inflate(inflater)
        binding.eventVM = eventVM
        eventVM.initEventVM()
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        add_event.setOnClickListener {
            eventVM.saveEvent()
        }

        calendarEvent.setOnDateChangeListener { _, year, month, dayOfMonth ->
            val cal = Calendar.getInstance()
            cal.set(year, month, dayOfMonth)
            eventVM.dateLiveData.value = cal.timeInMillis
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode != AppCompatActivity.RESULT_OK)
            return


    }
}