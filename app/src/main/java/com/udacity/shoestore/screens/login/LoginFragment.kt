package com.udacity.shoestore.screens.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.textfield.TextInputLayout
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.LoginFragmentBinding

class LoginFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val binding: LoginFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.login_fragment,
            container, false)

        binding.loginSignUpButton.setOnClickListener {
            if(checkLoginFieldsComplete(binding)) {
                findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToWelcomeFragment())
            }
        }

        binding.loginSignInButton.setOnClickListener {
            if(checkLoginFieldsComplete(binding)) {
                findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToWelcomeFragment())
            }
        }

        return binding.root
    }

    private fun checkLoginFieldsComplete(binding: LoginFragmentBinding): Boolean {
        var isComplete = true

        isComplete = checkInputFieldComplete(binding.loginEmailTextField)
        isComplete = checkInputFieldComplete(binding.loginPasswordTextField)

        return isComplete
    }

    private fun checkInputFieldComplete(inputField: TextInputLayout) : Boolean {
        return if(inputField.editText == null || inputField.editText!!.text.isEmpty()) {
            inputField.error = "Please complete this field"
            false
        } else {
            inputField.error = null
            true
        }
    }

}