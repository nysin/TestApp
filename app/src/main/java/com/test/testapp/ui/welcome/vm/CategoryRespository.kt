package com.test.testapp.ui.welcome.vm

import com.test.testapp.net.ApiResponse
import com.test.testapp.net.Constants
import com.test.testapp.net.base.HttpClient
import com.test.testapp.ui.welcome.data.CategoryItem

class CategoryRespository {

    private val categoryService:CategoryService by lazy {
        HttpClient.getService(CategoryService::class.java,Constants.BASEURL)
    }
   suspend fun getCategoryList():ApiResponse<MutableList<CategoryItem>> {
        return categoryService.getCategoryList()
    }
}