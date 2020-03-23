package com.gojek.data.repo.entity

import com.gojek.domain.repo.entity.RepoEntity

class RepoDataMapper {

    fun mapToEntity(data: RepoData): RepoEntity {
        return RepoEntity(
            author = data.author,
            name = data.name,
            avatar = data.avatar,
            url = data.url,
            description = data.description,
            language = data.language,
            languageColor = data.languageColor,
            stars = data.stars,
            forks = data.forks
        )
    }
}