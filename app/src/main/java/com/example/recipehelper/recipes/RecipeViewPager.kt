package com.example.recipehelper.recipes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.recipehelper.R
import com.example.recipehelper.databinding.ViewPagerBinding
import kotlinx.android.synthetic.main.activity_main.*
import java.util.Observer

class RecipeViewPager : Fragment(){
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding : ViewPagerBinding = DataBindingUtil.inflate(inflater, R.layout.view_pager, container, false)
        val adapter = RecipeViewPagerAdapter(fragmentManager!!)
        binding.viewPager.adapter = adapter
        adapter.addFragment(AllRecipes(), "Internet")
        adapter.addFragment(SavedRecipes(), "Saved")
        binding.tabs.setupWithViewPager(binding.viewPager)


        return binding.root
    }

}