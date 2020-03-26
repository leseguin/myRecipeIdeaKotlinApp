package fr.iut.myapplication.ui.event

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fr.iut.myapplication.data.Event
import fr.iut.myapplication.data.persistance.database.EventDatabase
import kotlinx.coroutines.launch
import java.util.*

class EventViewModel(val eventId : Long) : ViewModel() {

    val eventDB = EventDatabase.getInstance().eventDao()
    var eventLV = MutableLiveData<Event>()

    fun initEventVM(){
        val event= eventDB.findById(eventId)
        eventLV.value = event
    }


    fun saveEvent() {
            viewModelScope.launch {
                eventDB.update(eventLV.value!!)
            }
    }


    val dateLiveData = MediatorLiveData<Long>()
    init {
        dateLiveData.addSource(eventLV) { dateLiveData.postValue(it?.date) }
        dateLiveData.observeForever { newDate ->
            eventLV.value?.let {
                if (it.date != newDate)
                    it.date = newDate
            }
        }
    }

}