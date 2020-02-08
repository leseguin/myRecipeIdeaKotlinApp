package fr.iut.myapplication.ui.recipeBook

import android.content.Context
import android.util.Log
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import fr.iut.myapplication.data.Recipe
import fr.iut.myapplication.data.RecipeBook
import fr.iut.myapplication.data.connexion.Connexion

class RecipeBookViewModel(val context: Context) : ViewModel() {
    val co = Connexion()
    val recipeBook =  RecipeBook(emptyList())

    val recipesLiveData = MediatorLiveData<List<Recipe>>()
    init {
       // recipesLiveData.addSource(recipeBook) { recipesLiveData.postValue(it?.) }
        recipesLiveData.observeForever { newRecipes ->
            recipeBook.let {
                if (it.recipes != newRecipes) {
                    it.recipes = newRecipes
                    Log.d("APIIII", it.recipes.toString())
                }


            }
        }
    }

    fun getRandomRecipes(){
        co.getRandomRecipes(context, this)
    }





}