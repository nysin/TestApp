package com.test.testapp.ui.productdetail.vm

import com.test.testapp.ui.productdetail.data.ProductDetailResponse
import retrofit2.http.GET
import retrofit2.http.Url

interface ProductService {

    @GET
    suspend fun getProductDetail(@Url url:String):ProductDetailResponse
}