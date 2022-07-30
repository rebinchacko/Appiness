package com.rebin.appies.network

object Repository {

    suspend fun getdata() = ApiClient.getApiClient().getdata()

}