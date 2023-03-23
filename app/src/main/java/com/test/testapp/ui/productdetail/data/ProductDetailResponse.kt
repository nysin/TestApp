package com.test.testapp.ui.productdetail.data

import com.google.gson.annotations.SerializedName

data class ProductDetailResponse(
    val `data`: ProductDetailResData,
    val lang: String
)

data class ProductDetailResData(
    val products: MutableList<ProductDetailItem>
)

data class Benefit(
    val description: String,
    val image: String,
    @SerializedName("summary-title")
    val summaryTitle: String
)

data class ProductDetailItem(
    val benefits: MutableList<Benefit>,
    @SerializedName("benefits-title")
    val benefitsTitle: String,
    @SerializedName("category-code")
    val categoryCode: String,
    @SerializedName("category-name")
    val categoryName: String,
    @SerializedName("cta-label-primary")
    val ctaLabelPrimary: String,
    @SerializedName("cta-url-primary")
    val ctaUrlPrimary: String,
    val features: List<Feature>,
    @SerializedName("features-title")
    val featuresTitle: String,
    @SerializedName("product-code")
    val productCode: String,
    @SerializedName("product-description")
    val productDescription: String,
    @SerializedName("product-image-landscape")
    val productImageLandscape: String,
    @SerializedName("product-name")
    val productName: String,
    @SerializedName("product-url")
    val productUrl: String,
    val promotions: MutableList<Promotion>
)

data class Feature(
    val `data`: String,
    val icon: String,
    val title: String
)
data class Promotion(
    @SerializedName("campaign-code")
    val campaignCode: String
)