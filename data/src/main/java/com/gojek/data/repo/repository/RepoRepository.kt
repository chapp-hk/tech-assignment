package com.gojek.data.repo.repository

import com.gojek.data.repo.entity.RepoData
import com.gojek.domain.repo.IRepoRepository
import com.gojek.domain.repo.entity.RepoEntity
import io.reactivex.Single

class RepoRepository(
    private val repoApi: RepoApi,
    private val repoDao: RepoDao
) : IRepoRepository {

    override fun getRepos(): Single<List<RepoEntity>> {
        return repoDao.getRepos()
            .filter { it.isNotEmpty() }
            .switchIfEmpty(repoApi.getRepositories().doOnSuccess(this::insertRepos))
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
                currentPeriodStars = it.currentPeriodStars
            )
        }
    }

    private fun insertRepos(repoList: List<RepoData>) {
        repoDao.deleteAllRepos()
        repoDao.insertRepos(repoList)
    }
}