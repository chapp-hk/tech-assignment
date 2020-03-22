package com.gojek.data.repo.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.gojek.data.adapter.ColorString
import com.gojek.data.adapter.FormattedNumber
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

    @PrimaryKey
    @Json(name = "url")
    val url: String = "",

    @Json(name = "description")
    val description: String = "",

    @Json(name = "language")
    val language: String = "",

    @Json(name = "languageColor")
    @ColorString
    val languageColor: Int = 0,

    @Json(name = "stars")
    @FormattedNumber
    val stars: String = "",

    @Json(name = "forks")
    @FormattedNumber
    val forks: String = "",

    @Json(name = "currentPeriodStars")
    val currentPeriodStars: Int = 0
)