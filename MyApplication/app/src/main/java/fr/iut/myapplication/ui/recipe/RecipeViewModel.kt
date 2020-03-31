package fr.iut.myapplication.ui.recipe

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fr.iut.myapplication.R
import fr.iut.myapplication.data.NEW_RECIPE_ID
import fr.iut.myapplication.data.Recipe
import fr.iut.myapplication.data.persistance.database.RecipeDatabase
import kotlinx.coroutines.launch
import java.io.Serializable

//Serializable for navigation
class RecipeViewModel : ViewModel(), Serializable{

    val recipeLV = MutableLiveData(Recipe())

    fun addRecipe(context: Context){
        val recipeDB = RecipeDatabase.getInstance().recipeDao()
        val recipeAdd = recipeLV.value ?: Recipe( context.getString(R.string.new_recipe))
        recipeAdd.summary = context.getString(R.string.summary_new_recipe) + " " + recipeAdd.spoonacularSourceUrl

        viewModelScope.launch {
            recipeDB.insert(recipeAdd)
        }
    }
}