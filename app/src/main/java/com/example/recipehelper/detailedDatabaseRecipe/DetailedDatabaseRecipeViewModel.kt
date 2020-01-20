package com.example.recipehelper.detailedDatabaseRecipe

import android.provider.ContactsContract
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.recipehelper.database.DatabaseRecipe
import com.example.recipehelper.database.RecipeDao
import kotlinx.coroutines.*

class DetailedDatabaseRecipeViewModel(val recipe : DatabaseRecipe?,private val recipeDao : RecipeDao) : ViewModel(){

//    private val _databaseRecipe = MutableLiveData<DatabaseRecipe?>()
//    val databaseRecipe : LiveData<DatabaseRecipe?>
//            get() = _databaseRecipe
//
//    init {
//        getDetailedRecipe(recipe)
//    }
//
//    private val viewModelJob = Job()
//    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.IO)
//
//    fun getDetailedRecipe(source : String){
//        coroutineScope.launch {
//            val recipe = recipeDao.getRecepie(source)
//            withContext(Dispatchers.Main){
//                _databaseRecipe.value = recipe
//            }
//        }
//    }

}