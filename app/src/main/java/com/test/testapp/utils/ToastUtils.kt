package com.test.testapp.utils

import android.widget.Toast
import com.test.testapp.TestApplication

object ToastUtils {

    fun showToast(msg:String) {
        Toast.makeText(TestApplication.mApplication,msg,Toast.LENGTH_SHORT).show()
    }
}