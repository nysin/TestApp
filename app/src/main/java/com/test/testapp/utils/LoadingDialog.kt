package com.test.testapp.utils

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import com.test.testapp.R

class LoadingDialog(context:Context):Dialog(context,R.style.loadingDialog) {

    init {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.base_loading)

    }
}