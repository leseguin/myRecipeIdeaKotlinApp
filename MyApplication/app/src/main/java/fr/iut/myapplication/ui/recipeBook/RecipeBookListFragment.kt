package fr.iut.myapplication.ui.recipeBook
import android.os.Bundle
import android.view.*
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import fr.iut.myapplication.R


import fr.iut.myapplication.data.persistance.database.RecipeDatabase
import kotlinx.android.synthetic.main.fragment_my_list_recipe.view.*
import android.content.Context
import android.util.Log
import androidx.lifecycle.Observer
import fr.iut.myapplication.data.NEW_RECIPE_ID
import fr.iut.myapplication.databinding.FragmentBookListRecipeBinding
import fr.iut.myapplication.databinding.FragmentBookRecipeBinding
import kotlinx.android.synthetic.main.fragment_book_list_recipe.*


class RecipeBookListFragment : Fragment(), RecipeBookRecyclerViewAdapter.Callbacks {

    private var recipeBookVM = RecipeBookListViewModel()

    private val recipeListAdapter =
        RecipeBookRecyclerViewAdapter(this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentBookListRecipeBinding.inflate(inflater)
        binding.recipeBookListVM = recipeBookVM
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recycler_view.adapter = recipeListAdapter

        fab_add_recipe.setOnClickListener {
            val bundle = bundleOf("extra_recipeid" to NEW_RECIPE_ID)
            findNavController().navigate(R.id.action_nav_recipe_book_to_recipeBookFragment, bundle)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        recipeBookVM.recipeBookLV.observe(this, Observer {
            recipeListAdapter.updateList(it)
        })
    }

    override fun onRecipeSelected(recipeId: Long) {
        val bundle = bundleOf("extra_recipeid" to recipeId)
        findNavController().navigate(R.id.action_nav_recipe_book_to_recipeBookFragment, bundle) // se deplacer entre les fragment

    }


}