package fr.iut.myapplication.ui.event

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import fr.iut.myapplication.data.persistance.database.EventDatabase


class EventListViewModel : ViewModel() {
    private val listEventDao = EventDatabase.getInstance().eventDao()
    val eventDB = listEventDao.getAll()
    val eventLV = MutableLiveData(eventDB)

}