package com.example.recipehelper.recipes

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.recipehelper.database.DatabaseRecipe
import com.example.recipehelper.database.RecipeDao
import kotlinx.coroutines.*

class SavedRecipesViewModel(val recipeDao: RecipeDao) : ViewModel(){

    private val _databaseRecipes = MutableLiveData<List<DatabaseRecipe>>()
    val databaseRecipes : LiveData<List<DatabaseRecipe>>
        get() = _databaseRecipes

    private val _navigateToDetailedDatabaseRecipe = MutableLiveData<DatabaseRecipe?>()
    val navigateToDetailedDatabaseRecipe : LiveData<DatabaseRecipe?>
        get() = _navigateToDetailedDatabaseRecipe


    private val viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.IO)




    fun getDatabaseRecipes() {

        coroutineScope.launch {
            val listResult = recipeDao.getAllRecipes()
            withContext(Dispatchers.Main) {

                _databaseRecipes.value = listResult
                Log.d("listResult", listResult.toString())

            }

        }
    }

    fun displayDetailedDatabaseRecipe(recipe:DatabaseRecipe){
        _navigateToDetailedDatabaseRecipe.value = recipe
    }
    fun navigateToDetailedDatabaseRecipeDone(){
        _navigateToDetailedDatabaseRecipe.value = null
    }
}