package com.example.hizligeliyoproject.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.hizligeliyoproject.R
import com.example.hizligeliyoproject.databinding.ActivitySecondBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class SecondActivity : AppCompatActivity() {
    lateinit var binding: ActivitySecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)

        binding.apply {
            setContentView(binding.root)
            val navView: BottomNavigationView = binding.secondNavView

            val navHostFragment =
                supportFragmentManager.findFragmentById(R.id.second_nav_host_fragment) as NavHostFragment?
            val navController = navHostFragment!!.navController

            navView.setupWithNavController(navController)
        }
    }
}