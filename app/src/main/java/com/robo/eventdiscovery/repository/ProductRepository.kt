package com.robo.eventdiscovery.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.robo.eventdiscovery.model.Product
import com.robo.eventdiscovery.network.ProductAPI
import javax.inject.Inject

class ProductRepository @Inject constructor(private val productAPI: ProductAPI) {

    private val _products = MutableLiveData<List<Product>>()
    val products: LiveData<List<Product>>
        get() = _products

    private val _loadingStatus = MutableLiveData<Boolean>()
    val loadingStatus: LiveData<Boolean> get() = _loadingStatus

    suspend fun getProducts(){
        _loadingStatus.postValue(true)
        val result = productAPI.getProducts()
        if(result.isSuccessful && result.body() != null){
//            productDB.getProductDAO().addProducts(result.body()!!)
            _products.postValue(result.body())
        }
        _loadingStatus.postValue(false)
    }

}