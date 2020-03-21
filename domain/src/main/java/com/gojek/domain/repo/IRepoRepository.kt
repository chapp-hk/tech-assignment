package com.gojek.domain.repo

import com.gojek.domain.repo.entity.RepoEntity
import io.reactivex.Single

interface IRepoRepository {

    fun getRepos(): Single<List<RepoEntity>>
}