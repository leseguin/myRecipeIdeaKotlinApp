package fr.iut.myapplication.ui.recipe

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import fr.iut.myapplication.R

import fr.iut.myapplication.databinding.FragmentRecipeBinding
import kotlinx.android.synthetic.main.fragment_recipe.*

class RecipeFragment : Fragment() {

    private lateinit var recipeVM: RecipeViewModel



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        recipeVM = RecipeFragmentArgs.fromBundle(arguments).recipeVM
        setHasOptionsMenu(true)
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val binding = FragmentRecipeBinding.inflate(inflater)
        binding.recipeVM = recipeVM
        binding.lifecycleOwner = this
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        add_recipe.setOnClickListener {
            recipeVM.addRecipe(requireContext())
            Snackbar.make(
                constraint_l_recipe,
                R.string.recipe_add_to_book,
                Snackbar.LENGTH_SHORT
            ).show()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode != AppCompatActivity.RESULT_OK)
            return


    }




}