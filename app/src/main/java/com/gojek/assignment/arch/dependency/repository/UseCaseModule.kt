package com.gojek.assignment.arch.dependency.repository

import com.gojek.domain.repo.IRepoRepository
import com.gojek.domain.repo.usecase.GetReposUseCase
import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import io.reactivex.annotations.SchedulerSupport
import javax.inject.Named

@Module(
    includes = [
        RepositoryModule::class]
)
class UseCaseModule {

    @Provides
    internal fun provideGetReposUseCase(
        @Named(SchedulerSupport.CUSTOM) mainScheduler: Scheduler,
        @Named(SchedulerSupport.IO) ioScheduler: Scheduler,
        repoRepository: IRepoRepository
    ): GetReposUseCase {
        return GetReposUseCase(mainScheduler, ioScheduler, repoRepository)
    }
}