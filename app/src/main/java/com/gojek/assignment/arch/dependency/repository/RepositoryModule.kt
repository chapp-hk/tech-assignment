package com.gojek.assignment.arch.dependency.repository

import com.gojek.data.repo.repository.RepoApi
import com.gojek.data.repo.repository.RepoDao
import com.gojek.data.repo.repository.RepoRepository
import com.gojek.domain.repo.IRepoRepository
import dagger.Module
import dagger.Provides

@Module(
    includes = [
        ApiModule::class,
        DaoModule::class,
        RxModule::class
    ]
)
class RepositoryModule {

    @Provides
    internal fun providesRepoRepository(repoApi: RepoApi, repoDao: RepoDao): IRepoRepository {
        return RepoRepository(repoApi, repoDao)
    }
}