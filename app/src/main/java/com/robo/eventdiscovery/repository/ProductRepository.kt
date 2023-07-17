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

    suspend fun getProducts(){
        val result = productAPI.getProducts()
        if(result.isSuccessful && result.body() != null){
            _products.postValue(result.body())
        }
    }

}