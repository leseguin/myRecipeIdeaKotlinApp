package fr.iut.myapplication.ui.recipe
import android.os.Bundle
import android.view.*
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import fr.iut.myapplication.R
import fr.iut.myapplication.data.Recipe
import fr.iut.myapplication.data.persistance.database.RecipeDatabase
import fr.iut.myapplication.ui.joke.JokeViewModel
import kotlinx.android.synthetic.main.fragment_joke.*
import kotlinx.android.synthetic.main.fragment_my_list_recipe.*
import kotlinx.android.synthetic.main.fragment_my_list_recipe.view.*
import kotlinx.android.synthetic.main.fragment_my_list_recipe.view.group_empty_view

class RecipeListFragment : Fragment(), RecipeRecyclerViewAdapter.Callbacks {


    //private var recipeList = RecipeDatabase.getInstance().recipeDao().getAll()

    private lateinit var recipeVM : RecipeViewModel
    private lateinit var recipeList : List<Recipe>

    private lateinit var recipeListAdapter : RecipeRecyclerViewAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        recipeVM = RecipeViewModel(requireContext())
        recipeVM.newRecipes()
        recipeList = emptyList()

        recipeVM.recipesLV.observeForever { recipeList = it.recipes}

        recipeListAdapter = RecipeRecyclerViewAdapter(recipeList, this)


        val view = inflater.inflate(R.layout.fragment_my_list_recipe, container, false)
        view.recycler_view.adapter = recipeListAdapter
        view.group_empty_view.visibility = if (recipeList.isEmpty()) View.VISIBLE else View.GONE
        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



    }

    override fun onResume() {
        super.onResume()
        updateList()
    }

    private fun updateList() {
        //recipeVM.newRecipes()
        //recipeListAdapter.updateList(recipeVM.recipesLV.value!!.recipes)
        group_empty_view.visibility = if (recipeList.isEmpty()) View.VISIBLE else View.GONE
    }

    override fun onRecipeSelected(recipeId: Long) {
        val bundle = bundleOf("extra_recipeid" to recipeId)
        findNavController().navigate(R.id.action_nav_recipes_to_recipeFragment, bundle)

    }


}