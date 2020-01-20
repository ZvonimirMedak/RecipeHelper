package com.example.recipehelper.logIn

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.recipehelper.database.User
import com.example.recipehelper.database.UserDao
import kotlinx.coroutines.*

class LogInViewModel(val userDao : UserDao) :ViewModel(){
    private val _navigateToRegisterFragment = MutableLiveData<Boolean?>()
    val navigateToRegisterFragment : LiveData<Boolean?>
        get() = _navigateToRegisterFragment


    private val _navigateToAllRecipesFragment = MutableLiveData<Boolean?>()
    val navigateToAllRecipesFragment : LiveData<Boolean?>
        get() = _navigateToAllRecipesFragment

    private val logInJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + logInJob)

    override fun onCleared() {
        super.onCleared()
        logInJob.cancel()
    }

    private fun navigationToRecipesWanted(){
        _navigateToAllRecipesFragment.value = true
    }

    fun navigationToRecipesDone(){
        _navigateToAllRecipesFragment.value = null
    }


    fun registrationWanted(){
        _navigateToRegisterFragment.value = true
        Log.d("msg", "zatrazeno")
    }

    fun navigationToRegisterDone(){
        _navigateToRegisterFragment.value  = null
    }

    private var currentUser : User? = null
    fun onVerificationClicked(username: String, password : String) {
        uiScope.launch {
            var done = false
            withContext(Dispatchers.IO){
                currentUser = userDao.getUser(username)
                if(currentUser != null){
                    if(passwordCheck(currentUser, password)){
                        done = true
                    }

                }

            }
            if(done == true)
                navigationToRecipesWanted()

        }


    }

    private fun passwordCheck(user: User?, password: String): Boolean{
        return user!!.password == password
    }
}