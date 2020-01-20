package com.example.recipehelper.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query


@Dao
interface IngredientDao {
    @Insert
    fun insertAllIngredients(ingredients: List<DatabaseIngredient>)
    @Query("SELECT * FROM ingredient_table WHERE recipeSource=:key")
    fun getAllRecipeIngredients(key : String) : List<DatabaseIngredient>?
}