package com.test.testapp.utils

import android.content.Context
import androidx.fragment.app.FragmentManager

class SingleDialogUtils {

    companion object {


        private var loading: LoadingDialog? = null
        fun showLoading(
            context: Context,
        ) {
            if (loading == null) {
                loading = LoadingDialog(context)
            }
            loading?.show()

        }

        fun dissLoading() {
            loading?.let {
                if (it.isShowing) {
                    it.cancel()
                }
            }

        }
    }
}
