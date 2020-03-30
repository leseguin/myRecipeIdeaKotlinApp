package fr.iut.myapplication.ui.recipe

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import fr.iut.myapplication.data.Recipe
import fr.iut.myapplication.data.RecipeBook
import fr.iut.myapplication.data.WebService.Connexion

class RecipeListViewModel(val context: Context ) : ViewModel(){

    val co = Connexion()
    var recipes = emptyList<Recipe>()

    val recipesLV = MutableLiveData(recipes)

    fun newRecipes() : MutableLiveData<RecipeBook> {
        return co.getRandomRecipes(context)
    }
}