package com.test.testapp.ui.wb

import android.annotation.SuppressLint
import android.net.http.SslError
import android.os.Build
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.webkit.*
import com.test.testapp.base.BaseActivity
import com.test.testapp.base.BaseViewModel
import com.test.testapp.databinding.ActivityCommonWebBinding
import com.test.testapp.utils.ToastUtils
import java.lang.reflect.Method

class CommonWebActivity : BaseActivity<ActivityCommonWebBinding>() {

    override fun getViewBinding(): ActivityCommonWebBinding {
        return ActivityCommonWebBinding.inflate(layoutInflater)
    }

    override fun initViews() {
        super.initViews()
        initWebViewSettings();

        val url = intent.getStringExtra("url")
        if (TextUtils.isEmpty(url)) {
            ToastUtils.showToast("the url is error")
            binding.loading.visibility = View.GONE
        } else {
            binding.loading.visibility = View.VISIBLE
            binding.wb.loadUrl(url!!)
        }


    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun initWebViewSettings() {
        val webView = binding.wb
        val settings = binding.wb.getSettings();

        settings.javaScriptEnabled = true; // 启用javascript
        settings.domStorageEnabled = true; // 支持HTML5中的一些控件标签
        settings.builtInZoomControls = false; // 自选，非必要
//处理http和https混合的问题
        settings.mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW;

        webView.webChromeClient = object : WebChromeClient() {
            override fun onProgressChanged(view: WebView?, newProgress: Int) {
                super.onProgressChanged(view, newProgress)
                Log.e("webview", "progress:$newProgress")
                if (newProgress >= 100) {
                    binding.loading.visibility = View.GONE
                }
            }
        };
        webView.webViewClient = object : WebViewClient() {

            override fun onReceivedSslError(
                view: WebView?,
                handler: SslErrorHandler?,
                error: SslError?
            ) {
                when (error?.primaryError) {
                    SslError.SSL_UNTRUSTED ->
                        // 证书有问题
                        handler?.proceed();
                    else ->
                        handler?.cancel();
                }
            }

        };
        settings.javaScriptCanOpenWindowsAutomatically = false; // 设置JS是否可以打开WebView新窗口

        settings.displayZoomControls = false; // 不显示缩放按钮
        settings.setGeolocationEnabled(true);//定位是否可用，默认为true。

        settings.useWideViewPort = true; // 将图片调整到适合WebView的大小
        settings.loadWithOverviewMode = true; // 自适应屏幕

        webView.isHorizontalScrollBarEnabled = false;//去掉webview的滚动条,水平不显示
        webView.isScrollbarFadingEnabled = true;
        webView.scrollBarStyle = View.SCROLLBARS_OUTSIDE_OVERLAY;
        webView.overScrollMode = View.OVER_SCROLL_NEVER; // 取消WebView中滚动或拖动到顶部、底部时的阴影
    }
}
