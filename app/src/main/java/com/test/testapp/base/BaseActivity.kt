package com.test.testapp.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.test.testapp.event.LoadingEvent
import com.test.testapp.ext.sendLoadingEvent
import com.test.testapp.utils.SingleDialogUtils
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import java.lang.reflect.ParameterizedType

abstract class BaseActivity<T : ViewBinding> : AppCompatActivity() {

    private lateinit var _binding: T
    protected val binding get() = _binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = getViewBinding()
        setContentView(_binding.root)
//        viewModel=getTheViewModel()!!
        initViews()

    }


    override fun onDestroy() {
        super.onDestroy()
    }


    protected abstract fun getViewBinding(): T



    open fun initViews() {}


    fun showLoading() {
        SingleDialogUtils.showLoading(this)
    }
    fun dissLoading() {
        SingleDialogUtils.dissLoading()
    }
}