package com.example.recipehelper.recipes

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.recipehelper.R
import com.example.recipehelper.database.AppDatabase
import com.example.recipehelper.databinding.SavedRecipesBinding

class SavedRecipes : Fragment(){
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding : SavedRecipesBinding = DataBindingUtil.inflate(inflater, R.layout.saved_recipes, container, false)
        binding.databaseRecipeRecycler.layoutManager = LinearLayoutManager(context)
        val application = requireNotNull(this.activity).application
        val database = AppDatabase.getInstance(application)
        val recipeDao = database.recipeDao
        val viewModel = SavedRecipesViewModel(recipeDao)
        val adapter = DatabaseRecipesRecyclerAdapter(DatabaseRecipesOnClickListener {
          viewModel.displayDetailedDatabaseRecipe(it)
        }, recipeDao)
        viewModel.getDatabaseRecipes()
        viewModel.databaseRecipes.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
        })
        binding.databaseRecipeRecycler.adapter = adapter
        viewModel.navigateToDetailedDatabaseRecipe.observe(viewLifecycleOwner, Observer {
            if(it != null){
                this.findNavController().navigate(
                SavedRecipesDirections.actionSavedRecipesToDetailedDatabaseRecipe(it))
                viewModel.navigateToDetailedDatabaseRecipeDone()
            }

            Log.d("msg", "nakon svega")
        })
        return binding.root
    }


    override fun onResume() {
        super.onResume()
        Log.d("resume", "pozvano")
        val application = requireNotNull(this.activity).application
        val database = AppDatabase.getInstance(application)
        val recipeDao = database.recipeDao
        val viewModel = SavedRecipesViewModel(recipeDao)
        val adapter = DatabaseRecipesRecyclerAdapter(DatabaseRecipesOnClickListener {
            viewModel.displayDetailedDatabaseRecipe(it)
        }, recipeDao)
        viewModel.getDatabaseRecipes()
        viewModel.databaseRecipes.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
        })

    }
}