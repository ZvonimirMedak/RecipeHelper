package com.example.recipehelper.registration

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.recipehelper.R
import com.example.recipehelper.database.AppDatabase
import com.example.recipehelper.databinding.RegistrationBinding

class RegistrationFragment : Fragment(){
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: RegistrationBinding = DataBindingUtil.inflate(inflater, R.layout.registration, container, false)
        val application = requireNotNull(this.activity).application
        val database = AppDatabase.getInstance(application)
        val userDao = database.userDao
        val registrationViewModel = RegistrationViewModel(userDao)
        binding.registrationViewModel = registrationViewModel

        registrationViewModel.navigateToLogIn.observe(this, Observer {
            if(it == true){
                this.findNavController().navigate(
                    RegistrationFragmentDirections.actionRegistrationFragmentToLogInFragment()
                )
                registrationViewModel.navigationToLogInDone()
            }
        })
        binding.doneButton.setOnClickListener {
            registrationViewModel.insertNewUser(binding.regUsernameEdit.text.toString(),
                binding.regPasswordEdit.text.toString(), binding.regConfirmpassEdit.text.toString())
        }
        return binding.root
    }
}