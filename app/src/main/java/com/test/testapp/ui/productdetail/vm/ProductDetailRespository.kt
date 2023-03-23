package com.test.testapp.ui.productdetail.vm

import androidx.lifecycle.MutableLiveData
import com.test.testapp.net.Constants
import com.test.testapp.net.base.HttpClient
import com.test.testapp.ui.productdetail.data.ProductDetailItem
import com.test.testapp.ui.productdetail.data.ProductDetailResponse

class ProductDetailRespository {

    private val productService:ProductService by lazy {
        HttpClient.getService(ProductService::class.java, Constants.BASEURL)
    }

    suspend fun getProductDetail(productDetailUrl:String):ProductDetailResponse {
       return productService.getProductDetail(productDetailUrl)

    }
}