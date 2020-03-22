package com.gojek.data.repo.repository

import com.gojek.data.repo.entity.BuiltByData
import com.gojek.data.repo.entity.RepoData
import com.gojek.domain.repo.IRepoRepository
import com.gojek.domain.repo.entity.BuiltByEntity
import com.gojek.domain.repo.entity.RepoEntity
import io.reactivex.Single

class RepoRepository(
    private val repoApi: RepoApi,
    private val repoDao: RepoDao
) : IRepoRepository {

    override fun getRepos(): Single<List<RepoEntity>> {
        return repoApi.getRepositories()
            .map(this::mapRepoDataToEntity)
    }

    private fun mapRepoDataToEntity(dataList: List<RepoData>): List<RepoEntity> {
        return dataList.map {
            RepoEntity(
                author = it.author,
                name = it.name,
                avatar = it.avatar,
                url = it.url,
                description = it.description,
                language = it.language,
                languageColor = it.languageColor,
                stars = it.stars,
                forks = it.forks,
                currentPeriodStars = it.currentPeriodStars,
                builtBy = mapBuiltByDataToEntity(it.builtBy)
            )
        }
    }

    private fun mapBuiltByDataToEntity(builtByDataList: List<BuiltByData>): List<BuiltByEntity> {
        return builtByDataList.map {
            BuiltByEntity(
                username = it.username,
                href = it.href,
                avatar = it.avatar
            )
        }
    }
}