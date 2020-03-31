package fr.iut.myapplication.ui.recipe
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.navigation.fragment.findNavController
import fr.iut.myapplication.R
import fr.iut.myapplication.data.NEW_RECIPE_ID
import fr.iut.myapplication.data.Recipe
import fr.iut.myapplication.data.persistance.database.RecipeDatabase
import fr.iut.myapplication.ui.joke.JokeViewModel
import fr.iut.myapplication.ui.recipeBook.RecipeBookViewModel
import fr.iut.myapplication.ui.utils.viewModelFactory
import kotlinx.android.synthetic.main.fragment_joke.*
import kotlinx.android.synthetic.main.fragment_my_list_recipe.*
import kotlinx.android.synthetic.main.fragment_my_list_recipe.view.*

class RecipeListFragment : Fragment(), RecipeRecyclerViewAdapter.Callbacks {

    private lateinit var recipeListVM : RecipeListViewModel

    private lateinit var recipeVM : RecipeViewModel

    private lateinit var recipeListAdapter : RecipeRecyclerViewAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        recipeListVM = ViewModelProvider(this, viewModelFactory { RecipeListViewModel(requireContext())}).get()
        recipeVM = ViewModelProvider(this, viewModelFactory { RecipeViewModel()}).get()
        recipeListAdapter = RecipeRecyclerViewAdapter(this)
        val view = inflater.inflate(R.layout.fragment_my_list_recipe, container, false)
        view.recycler_view.adapter = recipeListAdapter
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        zero_item.visibility = if(recipeListVM.recipes.isEmpty()) View.VISIBLE else View.GONE

        fab_random_recipes.setOnClickListener {
            getNewRecipes()
        }
    }

    override fun onRecipeSelected(recipe: Recipe) {
        recipeVM.recipeLV.value = recipe
        val dir =
            RecipeListFragmentDirections.actionNavRecipesToRecipeFragment(recipeVM)
        findNavController().navigate(dir)
    }

    private fun getNewRecipes(){
        recipeListVM.newRecipes().observeForever {
            recipeListAdapter.updateList(it.recipes)
            recipeListVM.recipes = it.recipes
            zero_item.visibility = if(recipeListVM.recipes.isEmpty()) View.VISIBLE else View.GONE
        }
    }


}