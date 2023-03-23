package com.test.testapp.ext

import android.text.Editable
import android.text.TextWatcher
import android.util.Patterns
import android.widget.EditText

fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(editable: Editable?) {
            afterTextChanged.invoke(editable.toString())
        }

        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
    })
}

 fun isUserNameValid(username: String): Boolean {
    return username.length in 3..8
}

 fun isEmailValid(email: String): Boolean {
    return if (email.contains('@')) {
        Patterns.EMAIL_ADDRESS.matcher(email).matches()
    } else {
        email.isNotBlank()
    }
}

// A placeholder password validation check
 fun isPasswordValid(password: String): Boolean {
    return password.length >=6
}