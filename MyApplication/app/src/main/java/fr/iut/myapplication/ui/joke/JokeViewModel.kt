package fr.iut.myapplication.ui.joke

import android.content.Context
import androidx.lifecycle.*
import fr.iut.myapplication.data.WebService.Connexion

class JokeViewModel(val context: Context) : ViewModel() {

    val co = Connexion()
    var jokeLV = MutableLiveData<String>()



    fun newJoke() {
        co.getFoodJoke(context, jokeLV)

    }

}


