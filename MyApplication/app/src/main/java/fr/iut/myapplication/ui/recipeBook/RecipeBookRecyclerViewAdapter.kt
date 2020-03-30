package fr.iut.myapplication.ui.recipeBook

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import fr.iut.myapplication.R
import fr.iut.myapplication.data.Recipe
import kotlinx.android.synthetic.main.item_list_recipe.view.*

class RecipeBookRecyclerViewAdapter(private val listener: Callbacks) :
RecyclerView.Adapter<RecipeBookRecyclerViewAdapter.RecipeViewHolder>() {

    private var recipeList = emptyList<Recipe>()

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) =
        holder.bind(recipeList[position])

    override fun getItemCount() = recipeList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        RecipeViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_list_recipe,
                parent,
                false
            ), listener
        )

    class RecipeViewHolder(itemView: View, listener: Callbacks) :
        RecyclerView.ViewHolder(itemView) {

        var recipe: Recipe? = null
            private set

        init {
            itemView.setOnClickListener { recipe?.let { listener.onRecipeSelected(it.id) } }
        }

        fun bind(recipe: Recipe) {
            this.recipe = recipe
            itemView.recipe_title.text = recipe.title
        }

    }

    fun updateList(recipeList: List<Recipe>) {
        this.recipeList = recipeList
        notifyDataSetChanged()
    }

    interface Callbacks {
        fun onRecipeSelected(recipeId: Long)
    }
}