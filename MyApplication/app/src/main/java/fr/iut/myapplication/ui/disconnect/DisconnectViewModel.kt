package fr.iut.myapplication.ui.disconnect

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import fr.iut.myapplication.data.WebService.Connexion

class DisconnectViewModel(context: Context) : ViewModel() {

    val co = Connexion()
    val jokeLV = co.getFoodJoke(context)




}