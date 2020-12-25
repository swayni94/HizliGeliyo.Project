package com.example.hizligeliyoproject.ui.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.hizligeliyoproject.databinding.ActivityCategoriesBinding

class CategoriesActivity : AppCompatActivity() {
    private val catagories : ArrayList<String> = ArrayList()

    private lateinit var binding : ActivityCategoriesBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCategoriesBinding.inflate(layoutInflater)
        setContentView(binding.root)

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
            val resultIntent = Intent()
            resultIntent.putStringArrayListExtra("categories", catagories)
            setResult(Activity.RESULT_OK, resultIntent)
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