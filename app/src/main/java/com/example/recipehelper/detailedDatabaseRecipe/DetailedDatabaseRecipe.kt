package com.example.recipehelper.detailedDatabaseRecipe

import android.os.Bundle
import android.provider.ContactsContract
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.example.recipehelper.R
import com.example.recipehelper.database.AppDatabase
import com.example.recipehelper.database.DatabaseRecipe
import com.example.recipehelper.databinding.DetailedDatabaseRecipesBinding

class DetailedDatabaseRecipe : Fragment(){
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: DetailedDatabaseRecipesBinding = DataBindingUtil.inflate(inflater, R.layout.detailed_database_recipes, container, false )
        val application = requireNotNull(this.activity).application
        val database = AppDatabase.getInstance(application)
        val recipeDao = database.recipeDao
        val databaseRecipe : DatabaseRecipe? = arguments!!.getParcelable("recipe")
        val viewModel = DetailedDatabaseRecipeViewModel(databaseRecipe, recipeDao)

        return binding.root
    }


    fun setupLayout(binding : DetailedDatabaseRecipesBinding, databaseRecipe: DatabaseRecipe?){
        Glide.with(binding.recipeDetailImage).load(databaseRecipe?.image).centerCrop().into(binding.recipeDetailImage)
        binding.recipeNameText.text = databaseRecipe?.recipeName


    }
}