package com.robo.eventdiscovery.network

import com.robo.eventdiscovery.model.Product
import retrofit2.Response
import retrofit2.http.GET

interface ProductAPI {

    @GET("products")
    suspend fun getProducts() : Response<List<Product>>
}