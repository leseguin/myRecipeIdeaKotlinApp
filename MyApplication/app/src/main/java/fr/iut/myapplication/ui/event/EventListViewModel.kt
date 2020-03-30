package fr.iut.myapplication.ui.event

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fr.iut.myapplication.data.persistance.database.EventDatabase
import kotlinx.coroutines.launch


class EventListViewModel : ViewModel() {
    private val dogRepo = EventDatabase.getInstance().eventDao()
    val eventDB = dogRepo.getAll()
    val eventLV = MutableLiveData(eventDB)

}