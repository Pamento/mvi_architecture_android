package com.pawel.mymvi.data.api

import com.pawel.mymvi.data.model.DogsFacts
import com.pawel.mymvi.data.model.User

interface ApiHelper {
    suspend fun getUsers(): List<User>
    suspend fun getDogFacts(url: String): DogsFacts
}