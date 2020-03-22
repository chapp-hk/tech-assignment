package com.gojek.domain.repo.entity

data class RepoEntity(
    val author: String = "",
    val name: String = "",
    val avatar: String = "",
    val url: String = "",
    val description: String = "",
    val language: String = "",
    val languageColor: Int = 0,
    val stars: String = "",
    val forks: String = "",
    val currentPeriodStars: Int = 0
)