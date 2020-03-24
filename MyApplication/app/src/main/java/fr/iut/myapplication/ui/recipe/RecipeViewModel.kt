package fr.iut.myapplication.ui.recipe

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import fr.iut.myapplication.data.RecipeBook
import fr.iut.myapplication.data.WebService.Connexion

class RecipeViewModel(val context: Context) : ViewModel() {

    val co = Connexion()
    var recipesLV = MutableLiveData<RecipeBook>()



    fun newRecipes() {
        co.getRandomRecipes(context, recipesLV)
    }


}