package fr.iut.myapplication.data.WebService

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import fr.iut.myapplication.data.FoodJoke
import fr.iut.myapplication.data.RecipeBook
import fr.iut.myapplication.ui.recipeBook.RecipeBookViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.launch


class Connexion {



    fun getFoodJoke(context : Context, jokeLV : MutableLiveData<String> ) {
        //var jokeLV = MutableLiveData<String>()
        val queue = Volley.newRequestQueue(context)
        val url = "https://api.spoonacular.com/food/jokes/random?apiKey=f10e039e5c954ddba4e64bc156f997db"
        val stringRequest = StringRequest(
            com.android.volley.Request.Method.GET, url,
            Response.Listener<String> { response ->
                val gson = Gson()
                val foodJoke: FoodJoke = gson.fromJson(response, FoodJoke::class.java)
                jokeLV.value = foodJoke.text
                Log.d( "API","> From JSON String:\n${foodJoke.text}")
            },
            Response.ErrorListener { Log.d("API", "ERROR API") })

        queue.add(stringRequest)
    }



    private fun todoFoodJoke (url : String, joke : String) : StringRequest{
        val stringRequest =  StringRequest(
            com.android.volley.Request.Method.GET, url,
            Response.Listener<String> { response ->
                val gson = Gson()
                val foodJoke: FoodJoke = gson.fromJson(response, FoodJoke::class.java)
                Log.d( "API","> From JSON String:\n${foodJoke.text}")
            },
            Response.ErrorListener { Log.d("API", "ERROR API")  }) // Ajouter un traitement : Message d'erreur

        return stringRequest
    }




    fun getRandomRecipes(context : Context, recipeLV : MutableLiveData<RecipeBook>){
        val queue = Volley.newRequestQueue(context)
        val url = "https://api.spoonacular.com/recipes/random?number=2&apiKey=f10e039e5c954ddba4e64bc156f997db"
        val stringRequest = StringRequest(
            com.android.volley.Request.Method.GET, url,
            Response.Listener<String> { response ->
                val gson = Gson()
                Log.d("API", response)
                val recipes : RecipeBook = gson.fromJson(response, RecipeBook::class.java)
                recipeLV.value = recipes
                Log.d("API", recipes.recipes.toString())
                recipes.recipes.forEach { Log.d("API", "Title : " + it.title) }

            },
            Response.ErrorListener { Log.d("API", "ERROR API") })

// Add the request to the RequestQueue
        queue.add(stringRequest)
    }
}