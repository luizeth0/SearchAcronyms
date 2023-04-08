package com.challenge.acronyms.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Var(
    @Json(name = "freq")
    val freq: Int? = null,
    @Json(name = "lf")
    val lf: String? = null,
    @Json(name = "since")
    val since: Int? = null
)