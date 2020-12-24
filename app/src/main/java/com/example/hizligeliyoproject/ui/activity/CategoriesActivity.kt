package com.example.hizligeliyoproject.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.hizligeliyoproject.databinding.ActivityCategoriesBinding
import com.example.hizligeliyoproject.network.helper.ICatagoryFilter
import com.example.hizligeliyoproject.ui.viewmodel.HomeViewModel

class CategoriesActivity : AppCompatActivity() {
    private val catagories : ArrayList<String> = ArrayList()

    private lateinit var binding : ActivityCategoriesBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCategoriesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val listener : ICatagoryFilter = HomeViewModel().newInstance()

        binding.electronic.setOnClickListener {
            filtreControl("electronics")
        }
        binding.jewelery.setOnClickListener {
            filtreControl("jewelery")
        }
        binding.menClothing.setOnClickListener {
            filtreControl("men clothing")
        }
        binding.womenClothing.setOnClickListener {
            filtreControl("women clothing")
        }
        binding.catagoryFilterButton.setOnClickListener {
            listener.onCatagoryFilter(catagories)
            finish()
        }
        binding.toolbar.setNavigationOnClickListener {
            finish()
        }

    }

    private fun filtreControl(category : String){
        var booleanCatagory = false
        if (catagories.isEmpty()){
            catagories.add(category)
        }else{
            catagories.forEach {
                if (it.equals(category)) {
                    booleanCatagory = true
                }
            }
            if (booleanCatagory){
                catagories.remove(category)
            }else{
                catagories.add(category)
            }
        }
    }
}