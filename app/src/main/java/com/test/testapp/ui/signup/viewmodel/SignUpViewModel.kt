package com.test.testapp.ui.signup.viewmodel

import android.media.metrics.Event
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.testapp.R
import com.test.testapp.base.BaseViewModel
import com.test.testapp.ext.isEmailValid
import com.test.testapp.ext.isPasswordValid
import com.test.testapp.ext.isUserNameValid
import com.test.testapp.ext.sendLoadingEvent
import com.test.testapp.store.User
import com.test.testapp.store.UserRespository
import com.test.testapp.ui.signup.model.SignUpFormState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class SignUpViewModel(private val respository: UserRespository) : BaseViewModel() {

    val signUpLiveData = MutableLiveData<Long?>()

    fun signUp(userName:String,password: String,email: String,onStart:(()->Unit)?=null,onCompletion:(()->Unit)?=null) {
        viewModelScope.launch {
            val user = User(userName = userName, password = password, email = email)
            flow {
                val insertId = respository.insert(user)
                emit(insertId)
            }.flowOn(Dispatchers.IO)
                .onStart {
                    onStart?.invoke()
                }
                .onCompletion {
                   onCompletion?.invoke()
                }.catch {
                    signUpLiveData.postValue(null)
                   onCompletion?.invoke()
                }.collect{
                    signUpLiveData.postValue(it)

                }

        }
    }

    val signUpValidData = MutableLiveData<User?>()

    fun signUpValid(userName: String,onStart:(()->Unit)?=null,onCompletion:(()->Unit)?=null) {
        viewModelScope.launch {
            flow {
                val hasUser = respository.findUserOnlyByUsername(userName)
                emit(hasUser)
            }.flowOn(Dispatchers.IO)
                .onStart {
                    onStart?.invoke()
                }.onCompletion {
                    onCompletion?.invoke()
                }.catch {
                    onCompletion?.invoke()
                    signUpValidData.postValue(null)
                }.collect{
                    signUpValidData.postValue(it)
                }
        }
    }


    val signUpFormState = MutableLiveData<SignUpFormState>()

    fun signUpTextChange(userName:String,password:String,email:String) {
        if (!isUserNameValid(userName)) {
            signUpFormState.value = SignUpFormState(userNameError = R.string.invalid_username)
        }else if (!isPasswordValid(password)) {
            signUpFormState.value = SignUpFormState(passwordError = R.string.invalid_password)
        }else if (!isEmailValid(email)) {
            signUpFormState.value = SignUpFormState(emailError =R.string.invalid_email)
        }else {
            signUpFormState.value = SignUpFormState(isDataValid = true)
        }

    }


}