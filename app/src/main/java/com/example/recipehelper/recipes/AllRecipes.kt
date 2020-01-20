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
import com.example.recipehelper.databinding.AllRecipesBinding

class AllRecipes : Fragment(){



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding : AllRecipesBinding = DataBindingUtil.inflate(inflater, R.layout.all_recipes, container, false)
        binding.recipeRecycler.layoutManager = LinearLayoutManager(context)

        val viewModel = AllRecipesViewModel()
        binding.viewModel = viewModel
        val adapter = AllRecipesRecyclerAdapter(RecipeOnClickListener {
            viewModel.displayDetailedRecipe(it)

        })
        binding.recipeRecycler.adapter = adapter
        binding.setLifecycleOwner(this)
        viewModel.recipes.observe(this, Observer {
            if(it != null){
                adapter.submitList(viewModel.recipes.value)
            }
        })
        viewModel.navigateToDetailedRecipe.observe(this, androidx.lifecycle.Observer {
            if(it != null){
                Log.d("navmsg", "prije")
                this.findNavController().navigate(
                    RecipeViewPagerDirections.actionRecipeViewPagerToDetailedRecipe(it)
                )
                Log.d("navmsg", "nakon")
                Log.d("msg", viewModel.navigateToDetailedRecipe.value.toString())
                viewModel.navigateToDetailedRecipeDone()
            }
        })

        binding.searchButton.setOnClickListener {
            viewModel.getSearchedRecipes(binding.searchEdit.text.toString())
        }


        return binding.root
    }
}