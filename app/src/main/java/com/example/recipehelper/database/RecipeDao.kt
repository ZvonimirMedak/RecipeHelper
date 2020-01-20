package com.example.recipehelper.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface RecipeDao{
    @Insert
    fun insert(recipe: DatabaseRecipe)

    @Query("DELETE FROM saved_recipes WHERE source =:key")
    fun deleteRecipe(key: String)

    @Query("SELECT * FROM saved_recipes")
    fun getAllRecipes() : List<DatabaseRecipe>?

    @Query("SELECT * FROM saved_recipes WHERE source =:key")
    fun getRecepie(key : String):  DatabaseRecipe
}