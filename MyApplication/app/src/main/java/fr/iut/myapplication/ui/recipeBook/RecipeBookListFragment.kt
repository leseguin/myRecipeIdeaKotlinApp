package fr.iut.myapplication.ui.recipeBook
import android.os.Bundle
import android.view.*
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.navigation.fragment.findNavController
import fr.iut.myapplication.R
import fr.iut.myapplication.data.Recipe

import androidx.lifecycle.get


import fr.iut.myapplication.data.persistance.database.RecipeDatabase
import fr.iut.myapplication.ui.utils.viewModelFactory
import kotlinx.android.synthetic.main.fragment_my_list_recipe.*
import kotlinx.android.synthetic.main.fragment_my_list_recipe.view.*
import kotlinx.android.synthetic.main.fragment_my_list_recipe.view.group_empty_view
import java.util.Observer
import androidx.lifecycle.ViewModelProviders
import android.content.Context


class RecipeBookListFragment : Fragment(), RecipeBookRecyclerViewAdapter.Callbacks {


    //DEMANDER UN LISTE DE RECETTE AU HASARD


    private lateinit var recipeBookViewModel : RecipeBookViewModel

    private  var recipeList = RecipeDatabase.getInstance().recipeDao().getAll()




//    private var recipeList = RecipeDatabase.getInstance().recipeDao().getAll()
    private lateinit var recipeListAdapter :RecipeBookRecyclerViewAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        recipeListAdapter = RecipeBookRecyclerViewAdapter(recipeList, this)
        val view = inflater.inflate(R.layout.fragment_my_book_recipe, container, false)
        view.recycler_view.adapter = recipeListAdapter
        view.group_empty_view.visibility = if (recipeList.isEmpty()) View.VISIBLE else View.GONE
        return view
    }

    override fun onResume() {
        super.onResume()
        updateList()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        //recipeBookViewModel = ViewModelProvider(this, viewModelFactory { RecipeBookViewModel(requireContext())}).get()

    }

    private fun updateList() {
        recipeListAdapter.updateList(RecipeDatabase.getInstance().recipeDao().getAll())
        group_empty_view.visibility = if (recipeList.isEmpty()) View.VISIBLE else View.GONE
    }

    override fun onRecipeSelected(recipeId: Long) {
        val bundle = bundleOf("extra_recipeid" to recipeId)
        findNavController().navigate(R.id.action_nav_recipe_book_to_recipeBookFragment, bundle) // se deplacer entre les fragment

    }


}