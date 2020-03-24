package com.gojek.data.repo.repository

import com.gojek.data.local.IAppPreference
import com.gojek.data.repo.entity.RepoData
import com.gojek.data.repo.entity.RepoDataMapper
import com.gojek.domain.repo.IRepoRepository
import com.gojek.domain.repo.entity.RepoEntity
import io.reactivex.Single

class RepoRepository(
    private val repoApi: RepoApi,
    private val repoDao: RepoDao,
    private val appPreference: IAppPreference
) : IRepoRepository {

    private val repoDataMapper = RepoDataMapper()

    override fun getRepos(shouldForceUpdate: Boolean): Single<List<RepoEntity>> {
        return if (appPreference.isDataExpired() || shouldForceUpdate) {
            getReposFromNetwork()
        } else {
            getReposFromLocal()
        }.map(this::mapRepoDataToEntity)
    }

    private fun getReposFromNetwork(): Single<List<RepoData>> {
        return repoApi.getRepos().doOnSuccess(this::insertRepos)
    }

    private fun getReposFromLocal(): Single<List<RepoData>> {
        return repoDao.getRepos()
            .filter { it.isNotEmpty() }
            .switchIfEmpty(getReposFromNetwork())
    }

    private fun mapRepoDataToEntity(dataList: List<RepoData>): List<RepoEntity> {
        return dataList.map(repoDataMapper::mapToEntity)
    }

    private fun insertRepos(repoList: List<RepoData>) {
        repoDao.deleteAllRepos()
        repoDao.insertRepos(repoList)
        appPreference.setDataTime(System.currentTimeMillis())
    }
}