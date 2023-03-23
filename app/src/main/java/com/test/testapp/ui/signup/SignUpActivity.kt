package com.test.testapp.ui.signup

import android.content.Intent
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.activity.result.ActivityResult
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.ViewModelProvider
import com.test.testapp.base.BaseActivity
import com.test.testapp.databinding.ActivitySignUpBinding
import com.test.testapp.ext.afterTextChanged
import com.test.testapp.store.AppDataBase
import com.test.testapp.store.UserDao
import com.test.testapp.store.UserRespository
import com.test.testapp.ui.signup.viewmodel.SignUpViewFactory
import com.test.testapp.ui.signup.viewmodel.SignUpViewModel
import com.test.testapp.utils.ToastUtils

class SignUpActivity:BaseActivity<ActivitySignUpBinding>() {

    private lateinit var  signUpViewModel: SignUpViewModel

    override fun getViewBinding(): ActivitySignUpBinding {
        return ActivitySignUpBinding.inflate(layoutInflater)
    }

    private lateinit var userDao:UserDao
    override fun initViews() {
        super.initViews()
        userDao = AppDataBase.getInstance(application).userDao
        signUpViewModel = ViewModelProvider(
            this,
            SignUpViewFactory(UserRespository(userDao = userDao))
        ).get(SignUpViewModel::class.java)

        signUpViewModel.signUpFormState.observe(this) {
            val loginState = it ?: return@observe

            binding.signUp.isEnabled = loginState.isDataValid

            if (loginState.userNameError != null) {
                binding.username.error = getString(loginState.userNameError)
            }
            if (loginState.passwordError != null) {
              binding.password.error = getString(loginState.passwordError)
            }
            if (loginState.emailError != null) {
                binding.email.error = getString(loginState.emailError)
            }
        }

        signUpViewModel.signUpLiveData.observe(this) {
            if (it != null) {
                ToastUtils.showToast("registered successful")
                val intent= Intent()
                intent.putExtra("userName",binding.username.text.toString())
                finish()
            }else {
                ToastUtils.showToast("registered failed")
            }
        }


        binding.username
            .afterTextChanged {
                signUpViewModel.signUpTextChange(
                    binding.username.text.toString(),
                    binding.password.text.toString(),
                    binding.email.text.toString()
                )
            }

        binding.password
            .afterTextChanged {
                signUpViewModel.signUpTextChange(
                    binding.username.text.toString(),
                    binding.password.text.toString(),
                    binding.email.text.toString()
                )
            }

        signUpViewModel.signUpValidData.observe(this){
            if (it == null) {
                signUpViewModel.signUp(binding.username.text.toString(),
                    binding.password.text.toString(),
                    binding.email.text.toString(),{showLoading()},{dissLoading()})
            }else {
                ToastUtils.showToast("the username was registered")
            }
        }

        binding.email.apply {
            afterTextChanged {
                signUpViewModel.signUpTextChange(
                    binding.username.text.toString(),
                    binding.password.text.toString(),
                    binding.email.text.toString()
                )
            }

            setOnEditorActionListener { _, actionId, _ ->
                when (actionId) {
                    EditorInfo.IME_ACTION_DONE ->
                        signUpViewModel.signUp(
                            binding.username.text.toString(),
                            binding.password.text.toString(),
                            binding.email.text.toString(),{showLoading()},{dissLoading()}
                        )
                }
                false
            }

            binding.signUp.setOnClickListener {
                signUpViewModel.signUpValid(binding.username.text.toString(),{showLoading()},{dissLoading()})

            }

        }


    }


}