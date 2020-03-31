package fr.iut.myapplication.ui.recipeBook

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fr.iut.myapplication.data.NEW_RECIPE_ID
import fr.iut.myapplication.data.Recipe
import fr.iut.myapplication.data.persistance.database.RecipeDatabase
import kotlinx.coroutines.launch

class RecipeBookViewModel(recipeId : Long) : ViewModel() {

    private val recipeDB = RecipeDatabase.getInstance().recipeDao()
    val recipeLV = if (recipeId == NEW_RECIPE_ID) MutableLiveData(Recipe()) else MutableLiveData(
        recipeDB.findById(recipeId)
    )

    fun saveRecipe(): Boolean {
        recipeLV.value?.let {
            if (it.title.isBlank())
                return false
            else {
                viewModelScope.launch {
                    if (it.id == NEW_RECIPE_ID) recipeDB.insert(it) else recipeDB.update(it)
                }
                return true
            }
        }
        return false
    }

    fun deleteRecipe() {
        viewModelScope.launch {
            recipeLV.value?.let { if (it.id != NEW_RECIPE_ID) recipeDB.delete(it)}
        }
    }
}