package com.rebin.appies.network

import retrofit2.http.GET

interface Api {

    @GET("getMyList")
    suspend fun getdata():ResponseModel
}