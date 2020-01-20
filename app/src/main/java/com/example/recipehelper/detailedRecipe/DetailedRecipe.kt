package com.example.recipehelper.detailedRecipe

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recipehelper.R
import com.example.recipehelper.database.AppDatabase
import com.example.recipehelper.databinding.DetailedRecipeBinding
import com.example.recipehelper.network.Recipe

class DetailedRecipe : Fragment(){
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding : DetailedRecipeBinding = DataBindingUtil.inflate(inflater, R.layout.detailed_recipe, container, false)
        val application = requireNotNull(this.activity).application
        val database = AppDatabase.getInstance(application)
        val recipeDao = database.recipeDao
        val ingredientDao = database.ingredientDao
        val viewModel = DetailedRecipeViewModel(arguments!!.getParcelable("recipe")!!, recipeDao, ingredientDao)

        val adapter = DetailedRecipeRecyclerAdapter()
        binding.ingredientsRecyclerView.adapter = adapter
        binding.ingredientsRecyclerView.layoutManager = LinearLayoutManager(context)
        adapter.submitList(viewModel.recipe.ingredients)
        viewModel.setupImage(binding.recipeDetailImage)
        val packageManager = activity!!.packageManager
        binding.browserOpeningButton.setOnClickListener {
            viewModel.openWebPage(context!!, packageManager)
        }
        binding.saveRecipeButton.setOnClickListener {
           viewModel.saveRecipe()

        }

        viewModel.navigateToSavedRecipes.observe(this, Observer {
            if(it == true){
                this.findNavController().navigate(
                    DetailedRecipeDirections.actionDetailedRecipeToRecipeViewPager()
                )
                viewModel.navigateToSavedRecipesDone()
            }
        })


        return binding.root
    }
}