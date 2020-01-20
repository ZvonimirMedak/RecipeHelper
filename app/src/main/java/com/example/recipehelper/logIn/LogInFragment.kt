package com.example.recipehelper.logIn

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.recipehelper.R
import com.example.recipehelper.database.AppDatabase
import com.example.recipehelper.databinding.LogInBinding

class LogInFragment : Fragment(){
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding : LogInBinding = DataBindingUtil.inflate(inflater, R.layout.log_in, container, false)

        val application = requireNotNull(this.activity).application
        val database = AppDatabase.getInstance(application)
        val userDao = database.userDao
        val logInViewModel = LogInViewModel(userDao)
        binding.logInViewModel = logInViewModel

        binding.logInButton.setOnClickListener {
            logInViewModel.onVerificationClicked(binding.usernameEdit.text.toString(),
                binding.passwordEdit.text.toString())
        }

        logInViewModel.navigateToAllRecipesFragment.observe(this, Observer {
            if(it == true){
                this.findNavController().navigate(
                    LogInFragmentDirections.actionLogInFragmentToRecipeViewPager()
                )
                logInViewModel.navigationToRecipesDone()
            }
        })

        logInViewModel.navigateToRegisterFragment.observe(this, Observer {
            if(it == true){
                this.findNavController().navigate(
                    LogInFragmentDirections.actionLogInFragmentToRegistrationFragment()
                )
                logInViewModel.navigationToRegisterDone()

            }

        })

        return binding.root
    }
}