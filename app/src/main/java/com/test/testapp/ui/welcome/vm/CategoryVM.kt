package com.test.testapp.ui.welcome.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.test.testapp.base.BaseViewModel
import com.test.testapp.ext.sendLoadingEvent
import com.test.testapp.ui.welcome.data.CategoryItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class CategoryVM:BaseViewModel() {

    val categoryRes = MutableLiveData<MutableList<CategoryItem>?>()

    fun getCategory(onStart:(()->Unit)?=null,onCompletion:(()->Unit)?=null) {
        viewModelScope.launch {
            flow {
            val categoryRes = CategoryRespository().getCategoryList()
                emit(categoryRes.categories)
            }.flowOn(Dispatchers.IO)
                .onStart {
                    onStart?.invoke()
                }.onCompletion {
                    onCompletion?.invoke()
                }.catch {
                    onCompletion?.invoke()
                    categoryRes.postValue(null)
                }.collect{
                    categoryRes.postValue(it)
                }
        }
    }
}