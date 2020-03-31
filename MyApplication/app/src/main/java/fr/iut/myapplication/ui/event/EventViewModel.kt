package fr.iut.myapplication.ui.event

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fr.iut.myapplication.data.Event
import fr.iut.myapplication.data.NEW_EVENT_ID
import fr.iut.myapplication.data.persistance.database.EventDatabase
import kotlinx.coroutines.launch
import java.util.*

class EventViewModel(eventId : Long) : ViewModel() {

    private val eventDB = EventDatabase.getInstance().eventDao()
    val eventLV = if (eventId == NEW_EVENT_ID) MutableLiveData(Event()) else MutableLiveData(
        eventDB.findById(eventId)
    )

    fun saveEvent(): Boolean {
        eventLV.value?.let {
            if (it.name.isBlank())
                return false
            else {
                viewModelScope.launch {
                    if (it.id == NEW_EVENT_ID) eventDB.insert(it) else eventDB.update(it)
                }
                return true
            }
        }
        return false
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

    val guestNumberLiveData = MediatorLiveData<Int>()
    init {
        guestNumberLiveData.addSource(eventLV) { guestNumberLiveData.postValue(it?.numberGuest) }
        guestNumberLiveData.observeForever { newNbDate ->
            eventLV.value?.let {
                if (it.numberGuest != newNbDate)
                    it.numberGuest = newNbDate
            }
        }
    }

    fun lessGuest(num : Int) : Boolean {
        val cur = guestNumberLiveData.value
        if (cur != null) {
            if(cur.minus(num) >= 0 ) {
                guestNumberLiveData.value = guestNumberLiveData.value?.minus(num)
                return true
            }
            return false
        }
        return false
    }

    fun moreGuest(num : Int) {
        guestNumberLiveData.value = guestNumberLiveData.value?.plus(num)
    }

    fun deleteEvent() {
        viewModelScope.launch {
            eventLV.value?.let { if (it.id != NEW_EVENT_ID) eventDB.delete(it)}
        }
    }
}