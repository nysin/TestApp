package com.test.testapp.view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.test.testapp.R
import com.test.testapp.databinding.HeadViewBinding

class HeadView : ConstraintLayout {

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : this(
        context,
        attrs,
        0, 0
    )

    var binding: HeadViewBinding

    constructor(
        context: Context,
        attrs: AttributeSet?,
        defStyleAttr: Int,
        defStyleRes: Int
    ) : super(context, attrs, defStyleAttr, defStyleRes) {

        val root = View.inflate(context, R.layout.head_view, this)
        binding = HeadViewBinding.bind(root)
        binding.imgBack.setOnClickListener {
            if (context is AppCompatActivity) {
                context.finish()
            }
        }
    }

    fun setTitle(title:String) {
        binding.tvTitle.text = title
    }

}