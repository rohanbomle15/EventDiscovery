package com.robo.eventdiscovery.viewmodel

import com.robo.eventdiscovery.model.Product
import com.robo.eventdiscovery.repository.ProductRepository
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: ProductRepository) : ViewModel() {

    val productsLiveData : LiveData<List<Product>>
        get() = repository.products

    init {
        viewModelScope.launch {
            delay(10000)
            repository.getProducts()
        }
    }

}