package com.pawel.mymvi.data.api

import com.pawel.mymvi.data.model.DogsFacts
import com.pawel.mymvi.data.model.User

class ApiHelperImpl(private val apiService: ApiService): ApiHelper {
    override suspend fun getUsers(): List<User> {
        return apiService.getUsers()
    }

    override suspend fun getDogFacts(url: String): DogsFacts {
        return apiService.getDogsFacts(url)
    }
}