package com.example.recipehelper.recipes

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.recipehelper.network.NetworkRecipe
//import com.example.recipehelper.network.NetworkRecipe
import com.example.recipehelper.network.Recipe
import com.example.recipehelper.network.RecipeApi
import kotlinx.coroutines.*
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AllRecipesViewModel : ViewModel(){

    private val _recipes = MutableLiveData<List<Recipe>>()
    val recipes : LiveData<List<Recipe>>
        get() = _recipes

    private val _navigateToDetailedRecipe = MutableLiveData<Recipe>()
    val navigateToDetailedRecipe : LiveData<Recipe>
        get() = _navigateToDetailedRecipe

    private val recipeJob = Job()
    private val coroutineScope = CoroutineScope(recipeJob + Dispatchers.Main)


    init {
        getRecipes()
    }

    fun getSearchedRecipes(search : String) {
        if (search != "") {
            coroutineScope.launch {
                var call = RecipeApi.retrofitService.getRecipes(search)
                call.enqueue(object : Callback<NetworkRecipe> {
                    override fun onFailure(call: Call<NetworkRecipe>, t: Throwable) {
                        Log.d("msg", t.message)
                    }

                    override fun onResponse(
                        call: Call<NetworkRecipe>,
                        response: Response<NetworkRecipe>
                    ) {
                        var networkRecipe = response.body()!!
                        var listHit = networkRecipe.hits
                        var listResult = mutableListOf<Recipe>()
                        for (hit in listHit) {
                            listResult.add(hit.recipe)
                        }
                        _recipes.value = listResult

                    }

                })
            }
        }
    }

     private fun getRecipes(){
         coroutineScope.launch {
             var call = RecipeApi.retrofitService.getRecipes()
             call.enqueue(object: Callback<NetworkRecipe>{
                 override fun onFailure(call: Call<NetworkRecipe>, t: Throwable) {
                     Log.d("msg", t.message)
                 }

                 override fun onResponse(
                     call: Call<NetworkRecipe>,
                     response: Response<NetworkRecipe>
                 ) {
                     var networkRecipe= response.body()!!
                     var listHit = networkRecipe.hits
                     var listResult = mutableListOf<Recipe>()
                     for(hit in listHit){
                         listResult.add(hit.recipe)
                     }
                     _recipes.value = listResult

                 }

             })
         }
    }

    fun displayDetailedRecipe(networkRecipe:Recipe){
        _navigateToDetailedRecipe.value = networkRecipe
    }
    fun navigateToDetailedRecipeDone(){
        _navigateToDetailedRecipe.value = null
    }

    override fun onCleared() {
        super.onCleared()
        recipeJob.cancel()
    }
}
