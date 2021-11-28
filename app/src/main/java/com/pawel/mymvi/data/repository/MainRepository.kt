package com.pawel.mymvi.data.repository

import com.pawel.mymvi.data.api.ApiHelper
import com.pawel.mymvi.util.Constants.DOG_FACTS_URL

class MainRepository(private val apiHelper: ApiHelper) {
    suspend fun getUsers() = apiHelper.getUsers()
    suspend fun getDogsFacts() = apiHelper.getDogFacts(DOG_FACTS_URL)
}