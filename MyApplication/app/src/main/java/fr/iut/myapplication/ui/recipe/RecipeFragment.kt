package fr.iut.myapplication.ui.recipe

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import fr.iut.myapplication.R
import fr.iut.myapplication.data.NEW_RECIPE_ID
import fr.iut.myapplication.data.Recipe

import fr.iut.myapplication.data.persistance.database.RecipeDatabase
import kotlinx.android.synthetic.main.fragment_recipe.view.*

class RecipeFragment : Fragment()  {

    companion object {
        private const val EXTRA_RECIPE_ID = "extra_recipeid"

        fun newInstance(recipeId: Long) = RecipeFragment().apply {
            arguments = bundleOf(EXTRA_RECIPE_ID to recipeId)
        }
    }

    private lateinit var recipe : Recipe
    private var recipeId: Long = NEW_RECIPE_ID


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        recipeId = savedInstanceState?.getLong(EXTRA_RECIPE_ID) ?: arguments?.getLong(EXTRA_RECIPE_ID) ?: NEW_RECIPE_ID

        recipe = if (recipeId == NEW_RECIPE_ID) {
            activity?.setTitle(R.string.add_recipe_title)
            Recipe()
        } else {
            RecipeDatabase.getInstance().recipeDao().findById(recipeId)
        }

        setHasOptionsMenu(true)
    }


    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putLong(EXTRA_RECIPE_ID, recipeId)
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_recipe, container, false)

        view.recipeTitle.setText(recipe.title)

        return view
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode != AppCompatActivity.RESULT_OK)
            return


    }




}