package com.test.testapp.ui.productdetail.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.test.testapp.base.BaseViewModel
import com.test.testapp.ext.sendLoadingEvent
import com.test.testapp.ui.productdetail.data.ProductDetailItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class ProductDetailVM:BaseViewModel() {

    val productDetailRes = MutableLiveData<MutableList<ProductDetailItem>?>()

    fun getProductDetail(url:String,onStart:(()->Unit)?=null,onCompletion:(()->Unit)?=null) {
        viewModelScope.launch {
            flow {
                val result = ProductDetailRespository().getProductDetail(url)
                emit(result.data.products)
            }.flowOn(Dispatchers.IO)
                .onStart {
                    onStart?.invoke()
                }.onCompletion {
                    onCompletion?.invoke()
                }.catch {
                    onCompletion?.invoke()
                    productDetailRes.postValue(null)
                }.collect{
                    productDetailRes.postValue(it)
                }
        }
    }


}