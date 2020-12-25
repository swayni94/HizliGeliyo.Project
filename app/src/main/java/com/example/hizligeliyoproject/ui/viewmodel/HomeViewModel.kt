package com.example.hizligeliyoproject.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hizligeliyoproject.network.Repository
import com.example.hizligeliyoproject.network.model.Product
import kotlinx.coroutines.launch


class HomeViewModel : ViewModel() {
    private var repository:Repository = Repository()
    private var _productsLiveData = MutableLiveData<List<Product>>()
    private var _fullData = MutableLiveData<List<Product>>()
    val productsLiveData : LiveData<List<Product>>
        get() = _productsLiveData


    init {
        viewModelScope.launch {
            if (_fullData.value != null) {
                Log.e("homeViewModel", "_fulldata isn't null")
            } else {
                _fullData = repository.getProductsInfo()
                _productsLiveData = _fullData
            }
        }
    }

    fun setSearchQuery(search : String){
        val products = _fullData.value.orEmpty()
        _productsLiveData=repository.searchGetProduct(search, products)
    }

    fun setProductforCategoryFilter(categories: ArrayList<String>){
        val product = _fullData.value.orEmpty()
        _productsLiveData = repository.getCategoryProductList(categories, product)
    }
}