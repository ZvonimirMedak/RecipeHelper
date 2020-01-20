package com.example.recipehelper.recipes

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.recipehelper.databinding.RecipeListItemBinding
//import com.example.recipehelper.network.NetworkRecipe
import com.example.recipehelper.network.Recipe
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AllRecipesRecyclerAdapter (val recipesOnClick : RecipeOnClickListener) : ListAdapter<Recipe, AllRecipesRecyclerAdapter.ViewHolder>(RecipeDiffCallback()){



    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentRecipe = getItem(position)
        holder.bind(currentRecipe, recipesOnClick)
    }
    class ViewHolder private constructor(val binding : RecipeListItemBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(item: Recipe, onClickListener: RecipeOnClickListener){
            binding.recipe = item
            binding.onClickListener = onClickListener
            Glide.with(binding.recipeImage).load(item.image).centerCrop().into(binding.recipeImage)
            binding.recipeImage
            binding.executePendingBindings()
        }
        companion object{
            fun from(parent: ViewGroup): ViewHolder{
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding =RecipeListItemBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)

            }
        }
    }
}
class RecipeDiffCallback : DiffUtil.ItemCallback<Recipe>(){
    override fun areItemsTheSame(oldItem: Recipe, newItem: Recipe): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Recipe, newItem: Recipe): Boolean {
        return oldItem.source == newItem.source
    }
}

class RecipeOnClickListener (val onClickListener :(recipe: Recipe) -> Unit){
    fun onClick(recipe: Recipe) = onClickListener(recipe)
}



