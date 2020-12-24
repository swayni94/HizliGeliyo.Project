package com.example.hizligeliyoproject.ui.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class LoginViewModel: ViewModel() {

    val liveBooleanNextActivity = MutableLiveData<Boolean>()

    init {
        viewModelScope.launch {
            liveBooleanNextActivity.value = false
            Log.e("deneme", liveBooleanNextActivity.value.toString())
        }
    }

    fun launchMainActivity(email:String, password:String){
        if (email=="s@g.c" && password =="123")
        {
            Log.e("deneme", "giriş başarılı")
            liveBooleanNextActivity.value = true
            Log.e("deneme", liveBooleanNextActivity.value.toString())
        }
        else{
            Log.e("deneme", "giriş başarısız")
            liveBooleanNextActivity.value = false
            Log.e("deneme", liveBooleanNextActivity.value.toString())
        }
    }
}