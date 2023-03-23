package com.test.testapp.ui.signup.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.test.testapp.store.UserRespository
import java.lang.IllegalArgumentException

class SignUpViewFactory(private var respository: UserRespository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SignUpViewModel::class.java)) {
            return SignUpViewModel(respository) as T
        }
        throw IllegalArgumentException("Unknown View Model class")
    }

}