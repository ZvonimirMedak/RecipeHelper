package com.example.recipehelper.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [User::class, DatabaseRecipe::class, DatabaseIngredient::class], version = 2, exportSchema = false)
abstract class AppDatabase : RoomDatabase(){
    abstract val userDao: UserDao
    abstract val recipeDao: RecipeDao
    abstract val ingredientDao: IngredientDao
    companion object {
        private var INSTANCE: AppDatabase?  = null
        fun getInstance(context: Context) : AppDatabase {
            synchronized(this){
                var instance =
                    INSTANCE
                if(instance == null){
                    instance = Room.databaseBuilder(context, AppDatabase::class.java, "app_database")
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }

        }

    }
}