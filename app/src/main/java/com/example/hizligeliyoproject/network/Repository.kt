package com.example.hizligeliyoproject.network

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.hizligeliyoproject.network.model.Product
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class Repository {

    private val baseUrl = "https://fakestoreapi.com/"

    private fun getClient(): OkHttpClient {
        return OkHttpClient.Builder().readTimeout(1200,TimeUnit.SECONDS).connectTimeout(1200,TimeUnit.SECONDS).build()
    }

    private fun getRetrofit(): Retrofit {
        val client : OkHttpClient
        client = getClient()
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    fun getProductsInfo():MutableLiveData<List<Product>>{
        val datas = MutableLiveData<List<Product>>()
        val apiService : ApiService = getRetrofit().create(ApiService::class.java)
        val datalist : Call<List<Product>> = apiService.getProducts()
        datalist.enqueue(object : Callback<List<Product>>{
            override fun onResponse(call: Call<List<Product>>, response: Response<List<Product>>) {
                if (response.isSuccessful){
                    datas.value = response.body()
                    Log.e("repository","Product is successful")
                }
                else{
                    Log.e("repository","Product is success - failed")
                }
            }

            override fun onFailure(call: Call<List<Product>>, t: Throwable) {
                datas.value = null
                Log.e("repository","Product is failed")
            }
        })
        return datas
    }

    fun searchGetProduct(search:String, products : List<Product>):MutableLiveData<List<Product>>{
        val searchList: MutableLiveData<List<Product>> = MutableLiveData()
        val searchproducts : ArrayList<Product> = ArrayList()
        products.forEach {
            if (it.title.contains(search, true) || it.category.contains(search,  true)){
                searchproducts.add(it)
            }
        }
        searchList.value = searchproducts
        return searchList
    }

    fun getCategoryProductList(arrayList: ArrayList<String>, products: List<Product>) : MutableLiveData<List<Product>>{
        val categoryListProducts: MutableLiveData<List<Product>> = MutableLiveData()
        val resultProducts : ArrayList<Product> = ArrayList()
        arrayList.forEach{ item ->
            products.forEach{ product ->
                if (product.category == item){
                    resultProducts.add(product)
                }
            }
        }
        categoryListProducts.value = resultProducts
        return categoryListProducts
    }
}