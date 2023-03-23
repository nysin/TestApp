package com.test.testapp.ui.welcome.data

import com.google.gson.annotations.SerializedName

data class CategoryItem(

    @SerializedName("category-code")
    val categoryCode: String,
    @SerializedName("category-name")
    val categoryName: String,
    val products: MutableList<Product>,
    var isSelected:Boolean = false
)
