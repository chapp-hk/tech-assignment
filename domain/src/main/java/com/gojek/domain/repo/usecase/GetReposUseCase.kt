package com.gojek.domain.repo.usecase

import com.gojek.domain.repo.IRepoRepository
import com.gojek.domain.repo.entity.RepoEntity
import io.reactivex.Scheduler
import io.reactivex.Single

class GetReposUseCase(
    private val mainScheduler: Scheduler,
    private val ioScheduler: Scheduler,
    private val repoRepository: IRepoRepository
) {

    fun execute(): Single<List<RepoEntity>> {
        return repoRepository.getRepos()
            .subscribeOn(ioScheduler)
            .observeOn(mainScheduler)
    }
}