package com.example.recipehelper.recipes

import android.provider.ContactsContract
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.recipehelper.database.DatabaseRecipe
import com.example.recipehelper.database.RecipeDao
import com.example.recipehelper.databinding.DatabaseRecipeListItemBinding
import com.example.recipehelper.databinding.RecipeListItemBinding
import kotlinx.coroutines.*

class DatabaseRecipesRecyclerAdapter(private val databaseRecipeOnClick : DatabaseRecipesOnClickListener,private val recipeDao : RecipeDao) : ListAdapter<DatabaseRecipe, DatabaseRecipesRecyclerAdapter.ViewHolder>(DatabaseRecipeDiffCallback()){



    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentDatabaseRecipe = getItem(position)
        holder.bind(currentDatabaseRecipe, databaseRecipeOnClick)
    }
    class ViewHolder private constructor(val binding : DatabaseRecipeListItemBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(item: DatabaseRecipe, onClickListener: DatabaseRecipesOnClickListener){
            binding.recipe = item
            binding.onClickListener = onClickListener
            Glide.with(binding.databaseRecipeImage).load(item.image).centerCrop().into(binding.databaseRecipeImage)
            binding.executePendingBindings()
        }
        companion object{
            fun from(parent: ViewGroup): ViewHolder{
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = DatabaseRecipeListItemBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)

            }
        }
    }
}
class DatabaseRecipeDiffCallback : DiffUtil.ItemCallback<DatabaseRecipe>(){
    override fun areItemsTheSame(oldItem: DatabaseRecipe, newItem: DatabaseRecipe): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: DatabaseRecipe, newItem: DatabaseRecipe): Boolean {
        return oldItem.url == newItem.url
    }
}

class  DatabaseRecipesOnClickListener(val onClickListener: (databaseRecipe: DatabaseRecipe) -> Unit){
    fun onClick(databaseRecipe: DatabaseRecipe) = onClickListener(databaseRecipe)
}