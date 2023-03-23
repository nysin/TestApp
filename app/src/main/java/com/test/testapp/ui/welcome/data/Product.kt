package com.test.testapp.ui.welcome.data

import com.google.gson.annotations.SerializedName

data class Product(
    val category: String,
    @SerializedName("product-code")
    val productCode: String,
    @SerializedName("product-details")
    val productDetails: String,
    @SerializedName("product-name")
    val productName: String
):java.io.Serializable