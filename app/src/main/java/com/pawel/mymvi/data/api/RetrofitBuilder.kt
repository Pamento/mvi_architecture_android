package com.pawel.mymvi.data.api

import com.pawel.mymvi.util.Constants.USER_URL
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object RetrofitBuilder {

    private fun getRetrofit() = Retrofit.Builder()
        .baseUrl(USER_URL)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    val apiService: ApiService = getRetrofit().create(ApiService::class.java)
}