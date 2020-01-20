package com.example.recipehelper.recipes

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

class RecipeViewPagerAdapter (fragmentManager: FragmentManager) : FragmentStatePagerAdapter(fragmentManager){
    private var fragments = ArrayList<Fragment>()
    private var fragmentTitles = ArrayList<String>()


    override fun getItem(position: Int) : Fragment {
        return  fragments[position]
    }
    override fun getCount(): Int {
        return fragments.size
    }
    override fun getPageTitle(position: Int) : CharSequence? {
        return fragmentTitles[position]
    }
    fun addFragment(fragment : Fragment, title: String){
        fragments.add(fragment)
        fragmentTitles.add(title)
        notifyDataSetChanged()
    }

}