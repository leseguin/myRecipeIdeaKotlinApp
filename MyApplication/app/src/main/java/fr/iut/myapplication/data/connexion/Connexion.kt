package fr.iut.myapplication.data.connexion

import android.app.DownloadManager
import android.content.Context
import android.util.Log
import androidx.lifecycle.Observer
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import fr.iut.myapplication.data.FoodJoke
import fr.iut.myapplication.data.Recipe
import fr.iut.myapplication.data.RecipeBook
import fr.iut.myapplication.ui.recipeBook.RecipeBookViewModel


class Connexion {

    fun getFoodJoke(context : Context){

        val queue = Volley.newRequestQueue(context)
        val url = "https://api.spoonacular.com/food/jokes/random?apiKey=f10e039e5c954ddba4e64bc156f997db"
        val stringRequest = StringRequest(
            com.android.volley.Request.Method.GET, url,
            Response.Listener<String> { response ->
                val gson = Gson()
                val foodJoke: FoodJoke = gson.fromJson(response, FoodJoke::class.java)
                Log.d( "API","> From JSON String:\n${foodJoke.text}")
            },
            Response.ErrorListener { Log.d("API", "ERROR API") })

        queue.add(stringRequest)
    }



    fun getRandomRecipes(context : Context, recipeBookVM : RecipeBookViewModel){
        val queue = Volley.newRequestQueue(context)
        val url = "https://api.spoonacular.com/recipes/random?number=2&apiKey=f10e039e5c954ddba4e64bc156f997db"
        val stringRequest = StringRequest(
            com.android.volley.Request.Method.GET, url,
            Response.Listener<String> { response ->
                val gson = Gson()
                Log.d("API", response)
                val recipes : RecipeBook = gson.fromJson(response, RecipeBook::class.java)
                Log.d("API", recipes.recipes.toString())
                recipeBookVM.recipesLiveData.value = recipes.recipes
                recipes.recipes.forEach { Log.d("API", "Title : " + it.title) }

            },
            Response.ErrorListener { Log.d("API", "ERROR API") })

// Add the request to the RequestQueue
        queue.add(stringRequest)
    }
}