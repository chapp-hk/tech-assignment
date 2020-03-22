package com.gojek.data.repo.entity

import androidx.room.Embedded
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class BuiltByData(
    @Embedded(prefix = "repo_")
    @Json(name = "username")
    val username: String = "",

    @Embedded(prefix = "repo_")
    @Json(name = "href")
    val href: String = "",

    @Embedded(prefix = "repo_")
    @Json(name = "avatar")
    val avatar: String = ""
)