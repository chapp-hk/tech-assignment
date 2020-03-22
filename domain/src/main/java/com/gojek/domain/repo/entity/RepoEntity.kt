package com.gojek.domain.repo.entity

data class RepoEntity(
    val author: String = "",
    val name: String = "",
    val avatar: String = "",
    val url: String = "",
    val description: String = "",
    val language: String = "",
    val languageColor: String = "",
    val stars: Int = 0,
    val forks: Int = 0,
    val currentPeriodStars: Int = 0
)