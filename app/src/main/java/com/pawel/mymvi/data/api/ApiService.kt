package com.pawel.mymvi.data.api

import com.pawel.mymvi.data.model.DogsFacts
import com.pawel.mymvi.data.model.User
import retrofit2.http.GET
import retrofit2.http.Url

interface ApiService {

    @GET("users")
    suspend fun getUsers(): List<User>

    @GET
    suspend fun getDogsFacts(@Url url: String): DogsFacts
}