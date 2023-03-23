package com.test.testapp.ui.login

import android.app.Activity
import android.content.Intent
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.annotation.StringRes
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.Toast
import com.test.testapp.databinding.ActivityLoginBinding

import com.test.testapp.R
import com.test.testapp.base.BaseActivity
import com.test.testapp.ext.afterTextChanged
import com.test.testapp.store.AppDataBase
import com.test.testapp.store.UserRespository
import com.test.testapp.ui.signup.SignUpActivity
import com.test.testapp.ui.wb.CommonWebActivity
import com.test.testapp.ui.welcome.WelcomeActivity
import com.test.testapp.utils.ToastUtils

class LoginActivity : BaseActivity<ActivityLoginBinding>() {

    private lateinit var loginViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val userDao = AppDataBase.getInstance(this).userDao

        val username = binding.username
        val password = binding.password
        val login = binding.login

        loginViewModel = ViewModelProvider(this, LoginViewModelFactory(UserRespository(userDao))).get(LoginViewModel::class.java)

        loginViewModel.loginFormState.observe(this@LoginActivity, Observer {
            val loginState = it ?: return@Observer

            login?.isEnabled = loginState.isDataValid

            if (loginState.usernameError != null) {
                username.error = getString(loginState.usernameError)
            }
            if (loginState.passwordError != null) {
                password.error = getString(loginState.passwordError)
            }
        })

        loginViewModel.loginResult.observe(this@LoginActivity, Observer {

            if (it == null) {
                ToastUtils.showToast("the userName or password was error")
            } else {
                startActivity(Intent(this@LoginActivity, WelcomeActivity::class.java))
            }


        })

        username.afterTextChanged {
            loginViewModel.loginDataChanged(
                username.text.toString(),
                password.text.toString()
            )
        }

        password.apply {
            afterTextChanged {
                loginViewModel.loginDataChanged(
                    username.text.toString(),
                    password.text.toString()
                )
            }

            setOnEditorActionListener { _, actionId, _ ->
                when (actionId) {
                    EditorInfo.IME_ACTION_DONE ->
                        loginViewModel.login(
                            username.text.toString(),
                            password.text.toString(),{showLoading()},{dissLoading()}
                        )
                }
                false
            }

        }
        login?.setOnClickListener {
                loginViewModel.login(username.text.toString(), password.text.toString(),{showLoading()},{dissLoading()})
            }
        binding.tvSignUp?.setOnClickListener {
            val intent = Intent(this@LoginActivity, SignUpActivity::class.java)
            startActivity(intent)
        }
    }


    override fun getViewBinding(): ActivityLoginBinding {
        return ActivityLoginBinding.inflate(layoutInflater)
    }
}

/**
 * Extension function to simplify setting an afterTextChanged action to EditText components.
 */
