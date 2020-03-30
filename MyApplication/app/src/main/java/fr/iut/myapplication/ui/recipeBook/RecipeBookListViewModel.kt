package fr.iut.myapplication.ui.recipeBook

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import fr.iut.myapplication.data.persistance.database.RecipeDatabase

class RecipeBookListViewModel : ViewModel() {
    private val recipeRepo = RecipeDatabase.getInstance().recipeDao()
    val recipeBookDB = recipeRepo.getAll()
    val recipeBookLV = MutableLiveData(recipeBookDB)
}