package com.rebin.appies.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.rebin.appies.network.Repository
import com.rebin.appies.network.ResponseModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel:ViewModel() {


    var mresponse=MutableLiveData<ResponseModel>()
    var merror=MutableLiveData<String>()


    fun getdata() {

        viewModelScope.launch {
            val call = Repository.getdata()
            if (call != null) {
                call.apply {
                    if (this.statusCode == 200) {
                        mresponse.postValue(this)
                    } else {
                        merror.postValue("Something went wrong...")
                    }
                }
            }
        }
    }
}