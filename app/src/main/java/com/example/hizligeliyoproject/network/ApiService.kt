package com.example.hizligeliyoproject.network

import com.example.hizligeliyoproject.network.model.Product
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("products")
    fun getProducts():Call<List<Product>>
}