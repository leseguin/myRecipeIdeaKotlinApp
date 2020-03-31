package fr.iut.myapplication.ui.recipeBook

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import fr.iut.myapplication.R
import fr.iut.myapplication.data.NEW_EVENT_ID
import fr.iut.myapplication.data.NEW_RECIPE_ID
import fr.iut.myapplication.databinding.FragmentBookRecipeBinding
import fr.iut.myapplication.ui.utils.viewModelFactory
import kotlinx.android.synthetic.main.fragment_book_recipe.*
import kotlinx.android.synthetic.main.fragment_recipe.*
import kotlinx.android.synthetic.main.fragment_recipe.add_recipe

class RecipeBookFragment : Fragment()  {

    companion object {
        private const val EXTRA_RECIPE_ID = "extra_recipeid"
    }

    private var recipeId: Long = NEW_RECIPE_ID
    private lateinit var recipeBookVM: RecipeBookViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        recipeId = savedInstanceState?.getLong(EXTRA_RECIPE_ID) ?: arguments?.getLong(EXTRA_RECIPE_ID) ?: NEW_RECIPE_ID
        recipeBookVM = ViewModelProvider(this, viewModelFactory { RecipeBookViewModel(recipeId) }).get()
        setHasOptionsMenu(true)
    }


    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putLong(EXTRA_RECIPE_ID, recipeId)
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding = FragmentBookRecipeBinding.inflate(inflater)
        binding.recipeBookVM = recipeBookVM
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        add_recipe.setOnClickListener {
            if (!recipeBookVM.saveRecipe()) {
                Snackbar.make(
                    constraint_l_recipe,
                    R.string.error_create_event,// TODO(create String)
                    Snackbar.LENGTH_SHORT
                ).show()
            } else findNavController().navigate(R.id.action_recipeBookFragment_to_nav_recipe_book)
        }

        remove_recipe.setOnClickListener {
            deleteRecipe()
        }
    }


    private fun deleteRecipe() {
        if (recipeId != NEW_EVENT_ID) {
            AlertDialog.Builder(activity!!)
                .setTitle("Delete event")
                .setMessage("Do you really want to delete ${recipeBookVM.recipeLV.value!!.title} ? ")
                .setPositiveButton(android.R.string.yes) { dialog, which ->
                    recipeBookVM.deleteRecipe()
                    findNavController().navigate(R.id.action_recipeBookFragment_to_nav_recipe_book)
                }
                .setNegativeButton(android.R.string.no, null)
                .show()
        }
    }



    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode != AppCompatActivity.RESULT_OK)
            return

    }




}