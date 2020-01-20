package com.example.recipehelper.detailedRecipe

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.widget.ImageView
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.room.Database
import com.bumptech.glide.Glide
import com.example.recipehelper.database.DatabaseIngredient
import com.example.recipehelper.database.DatabaseRecipe
import com.example.recipehelper.database.IngredientDao
import com.example.recipehelper.database.RecipeDao
import com.example.recipehelper.network.Recipe
import kotlinx.coroutines.*

class DetailedRecipeViewModel(val recipe: Recipe,val recipeDao : RecipeDao, val ingredientDao: IngredientDao) : ViewModel(){


    private val _navigateToSavedRecipes = MutableLiveData<Boolean?>()
    val navigateToSavedRecipes : LiveData<Boolean?>
        get() = _navigateToSavedRecipes

    val viewModelJob = Job()
    val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)


    fun setupImage(image: ImageView){
        Glide.with(image).load(recipe.image).centerCrop().into(image)
    }
    fun openWebPage(context: Context, packageManager: PackageManager) {
        val webpage: Uri = Uri.parse(recipe.url)
        val intent = Intent(Intent.ACTION_VIEW, webpage)
        if (intent.resolveActivity(packageManager) != null) {
            context.startActivity(intent)
        }
    }
    fun saveRecipe(){
        coroutineScope.launch{
            withContext(Dispatchers.IO) {
                recipeDao.insert(convertToDatabaseRecipe())
                ingredientDao.insertAllIngredients(convertToDatabaseIngredient())
            }
        }
        navigateToSavedRecipes()

    }

    private fun convertToDatabaseIngredient(): List<DatabaseIngredient>{
        val listResult = mutableListOf<DatabaseIngredient>()
        for(ingredient in recipe.ingredients){
            listResult.add(DatabaseIngredient(name = ingredient.text, weight = ingredient.weight, recipeSource = recipe.source))
        }
        return listResult
    }

    private fun convertToDatabaseRecipe() = DatabaseRecipe(source = recipe.source, calories = recipe.calories, recipeName = recipe.label, url = recipe.url
        ,  image = recipe.image, timeToMake = recipe.totalTime)

    private fun navigateToSavedRecipes(){
        _navigateToSavedRecipes.value = true
    }

    fun navigateToSavedRecipesDone(){
        _navigateToSavedRecipes.value = null
    }

}