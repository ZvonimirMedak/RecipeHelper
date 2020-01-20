package com.example.recipehelper.database

import android.provider.ContactsContract
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "ingredient_table",
        foreignKeys =  [ForeignKey(entity = DatabaseRecipe::class,
    parentColumns = ["source"],
    childColumns = ["recipeSource"])])
data class DatabaseIngredient (
    @PrimaryKey(autoGenerate = true)
    val ingredientId : Long = 0L,
    val name : String,
    val weight : Double,
    @ColumnInfo(index = true)
    val recipeSource : String
)