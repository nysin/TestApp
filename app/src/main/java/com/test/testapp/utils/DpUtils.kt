package com.test.testapp.utils

import android.content.Context
import android.util.TypedValue
import com.test.testapp.TestApplication

object DpUtils {
    fun  dp2px(context: Context, dpValue:Float):Float {
            return TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                dpValue,
                context
                .resources.displayMetrics
            )
        }

}