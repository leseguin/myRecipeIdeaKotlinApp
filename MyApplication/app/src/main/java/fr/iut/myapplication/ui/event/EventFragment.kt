package fr.iut.myapplication.ui.event

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import fr.iut.myapplication.R
import fr.iut.myapplication.data.NEW_EVENT_ID
import fr.iut.myapplication.databinding.FragmentEventBinding
import kotlinx.android.synthetic.main.fragment_event.*
import com.google.android.material.snackbar.Snackbar
import java.util.*
import android.annotation.SuppressLint
import android.util.Log
import fr.iut.myapplication.data.persistance.converter.LongToStringConverter
import java.text.SimpleDateFormat


class EventFragment : Fragment() {

    companion object {
        private const val EXTRA_EVENT_ID = "extra_eventid"
    }

    private var eventId: Long = NEW_EVENT_ID
    private lateinit var eventVM: EventViewModel


    @Suppress("UNCHECKED_CAST")
    inline fun <VM : ViewModel> viewModelFactory(crossinline f: () -> VM) = object : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T = f() as T
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Get the event ID
        eventId = savedInstanceState?.getLong(EXTRA_EVENT_ID) ?: arguments?.getLong(EXTRA_EVENT_ID) ?: NEW_EVENT_ID

        setHasOptionsMenu(true)
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
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Add or update an event
        add_event.setOnClickListener {
            if(!eventVM.saveEvent()){
                Snackbar.make(
                    constraint_l_event,
                    R.string.error_create_event,
                    Snackbar.LENGTH_SHORT
                ).show()
            }
        }


        initCalendarView()
        initFabButtons()


    }

    fun initCalendarView(){
        val convert = LongToStringConverter

        val currDate = convert.convert(eventVM.eventLV.value!!.date)
        calendarEvent.setDate(
            SimpleDateFormat("dd/MM/yyyy").parse(currDate).time,
            true,
            true
        )
        // Update or add the date of an event
        calendarEvent.setOnDateChangeListener { _, year, month, dayOfMonth ->
            val cal = Calendar.getInstance()
            cal.set(year, month, dayOfMonth)
            eventVM.dateLiveData.value = cal.timeInMillis
        }
    }


    fun initFabButtons(){
        fab_revome_guest.setOnClickListener {
            if(!eventVM.lessGuest(1)) {
                showSnackBarErrorRMGuest()
            }
        }

        fab_revome_guest_more.setOnClickListener {
            if(!eventVM.lessGuest(10)) {
                showSnackBarErrorRMGuest()
            }
        }

        fab_add_guest.setOnClickListener {
            eventVM.moreGuest(1)
        }

        fab_add_guest_more.setOnClickListener {
            eventVM.moreGuest(10)
        }
    }

    fun showSnackBarErrorRMGuest(){
        Snackbar.make(
            constraint_l_event,
            R.string.error_remove_guest,
            Snackbar.LENGTH_SHORT
        ).show()
    }



    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode != AppCompatActivity.RESULT_OK)
            return


    }
}