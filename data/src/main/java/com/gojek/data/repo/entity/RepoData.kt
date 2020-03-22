package com.gojek.data.repo.entity

import androidx.room.Entity
import com.gojek.data.repo.entity.BuiltByData
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@Entity(tableName = "repo")
@JsonClass(generateAdapter = true)
data class RepoData(
    @Json(name = "author")
    val author: String = "",

    @Json(name = "name")
    val name: String = "",

    @Json(name = "avatar")
    val avatar: String = "",

    @Json(name = "url")
    val url: String = "",

    @Json(name = "description")
    val description: String = "",

    @Json(name = "language")
    val language: String = "",

    @Json(name = "languageColor")
    val languageColor: String = "",

    @Json(name = "stars")
    val stars: Int = 0,

    @Json(name = "forks")
    val forks: Int = 0,

    @Json(name = "currentPeriodStars")
    val currentPeriodStars: Int = 0,

    @Json(name = "builtBy")
    val builtBy: List<BuiltByData> = listOf()
)