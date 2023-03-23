package com.test.testapp.ui.signup.model

data class SignUpFormState(
    val userNameError:Int?= null,
    val passwordError:Int?= null,
    val emailError:Int?= null,
    val isDataValid:Boolean =false
)
