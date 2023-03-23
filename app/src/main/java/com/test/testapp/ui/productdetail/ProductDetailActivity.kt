package com.test.testapp.ui.productdetail

import android.content.Intent
import android.text.TextUtils
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.test.testapp.base.BaseActivity
import com.test.testapp.base.BaseViewModel
import com.test.testapp.databinding.ActivityProductDetailBinding
import com.test.testapp.databinding.ProductDetailItemBinding
import com.test.testapp.ui.productdetail.data.ProductDetailItem
import com.test.testapp.ui.productdetail.vm.ProductDetailVM
import com.test.testapp.ui.wb.CommonWebActivity
import com.test.testapp.utils.ToastUtils

class ProductDetailActivity:BaseActivity<ActivityProductDetailBinding>() {

    lateinit var productDetailAdapter:ProductDetailAdapter
    lateinit var productDetailVM:ProductDetailVM
    override fun getViewBinding(): ActivityProductDetailBinding {
        return ActivityProductDetailBinding.inflate(layoutInflater)
    }

    override fun initViews() {
        val productDetailUrl:String = intent.getStringExtra("productUrl")?:""
        binding.productDetailList.layoutManager= LinearLayoutManager(this)
        productDetailAdapter = ProductDetailAdapter()
        productDetailAdapter.onImgClick = {item: ProductDetailItem, pos: Int ->

            val intent =Intent (this@ProductDetailActivity,CommonWebActivity::class.java)
            intent.putExtra("url",item.productUrl)
            startActivity(intent)
        }
        binding.productDetailList.adapter = productDetailAdapter

        productDetailVM = ProductDetailVM()
        productDetailVM.productDetailRes.observe(this) {
            if (it == null) {
                ToastUtils.showToast("there is an error in requesting")
            }else {
                productDetailAdapter.setNewData(it)
            }
        }
        if (TextUtils.isEmpty(productDetailUrl)) {
            ToastUtils.showToast("the productDetail url must not be null")
        }
        productDetailVM.getProductDetail(productDetailUrl,{showLoading()},{dissLoading()})

    }



}