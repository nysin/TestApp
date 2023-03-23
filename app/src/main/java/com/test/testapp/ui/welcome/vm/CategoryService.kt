package com.test.testapp.ui.welcome.vm

import com.test.testapp.net.ApiResponse
import com.test.testapp.ui.welcome.data.CategoryItem
import retrofit2.http.GET

interface CategoryService {

    @GET("sg/rtob/categories.json")
    suspend fun getCategoryList():ApiResponse<MutableList<CategoryItem>>



}