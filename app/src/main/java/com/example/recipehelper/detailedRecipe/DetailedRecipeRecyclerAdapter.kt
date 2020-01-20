package com.example.recipehelper.detailedRecipe

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.recipehelper.databinding.IngredientListItemBinding
import com.example.recipehelper.databinding.RecipeListItemBinding
import com.example.recipehelper.network.Ingredient
import com.example.recipehelper.network.Recipe

class DetailedRecipeRecyclerAdapter : ListAdapter<Ingredient, DetailedRecipeRecyclerAdapter.ViewHolder>(IngredientDiffCallback()){
override fun onCreateViewHolder(
    parent: ViewGroup,
    viewType: Int
): ViewHolder {
    return ViewHolder.from(parent)
}

override fun onBindViewHolder(holder: DetailedRecipeRecyclerAdapter.ViewHolder, position: Int) {
    val currentIngredient= getItem(position)
    holder.bind(currentIngredient)
}
class ViewHolder private constructor(val binding : IngredientListItemBinding) : RecyclerView.ViewHolder(binding.root){
    fun bind(item: Ingredient){
        binding.ingredient = item
        binding.executePendingBindings()
    }
    companion object{
        fun from(parent: ViewGroup): ViewHolder{
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = IngredientListItemBinding.inflate(layoutInflater, parent, false)
            return ViewHolder(binding)

        }
    }
}
}
class IngredientDiffCallback : DiffUtil.ItemCallback<Ingredient>(){
    override fun areItemsTheSame(oldItem: Ingredient, newItem: Ingredient): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Ingredient, newItem: Ingredient): Boolean {
        return oldItem.text == newItem.text
    }
}


