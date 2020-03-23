package com.gojek.domain.repo

import com.gojek.domain.repo.entity.RepoEntity
import io.reactivex.Single

interface IRepoRepository {

    fun getRepos(shouldForceUpdate: Boolean): Single<List<RepoEntity>>
}