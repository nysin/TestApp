package com.test.testapp.ui.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope

import com.test.testapp.R
import com.test.testapp.base.BaseViewModel
import com.test.testapp.ext.isPasswordValid
import com.test.testapp.ext.isUserNameValid
import com.test.testapp.ext.sendLoadingEvent
import com.test.testapp.store.User
import com.test.testapp.store.UserRespository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class LoginViewModel(private val loginRepository: UserRespository) : BaseViewModel() {

    private val _loginForm = MutableLiveData<LoginFormState>()
    val loginFormState: LiveData<LoginFormState> = _loginForm

     val loginResult = MutableLiveData<User?>()

   fun login(username: String, password: String,onStart:(()->Unit)?=null,onCompletion:(()->Unit)?=null){
        viewModelScope.launch {
            flow {
                val user = loginRepository.login(userName = username,password=password);
                emit(user)
            }.flowOn(Dispatchers.IO)
                .onStart {
                    onStart?.invoke()
                }.onCompletion {
                    onCompletion?.invoke()
                }.catch {
                    Log.e("error",it.toString())
                    onCompletion?.invoke()
                    loginResult.postValue(null)
                }.collect{
                    loginResult.postValue(it)
                }
        }

    }

    fun loginDataChanged(username: String, password: String) {
        if (!isUserNameValid(username)) {
            _loginForm.value = LoginFormState(usernameError = R.string.invalid_username)
        } else if (!isPasswordValid(password)) {
            _loginForm.value = LoginFormState(passwordError = R.string.invalid_password)
        } else {
            _loginForm.value = LoginFormState(isDataValid = true)
        }
    }


}