package com.pawel.mymvi.data.model

import com.squareup.moshi.Json


class DogsFacts {
    @Json(name = "facts")
    var facts: List<String>? = null

    @Json(name = "success")
    var success: Boolean = false
}