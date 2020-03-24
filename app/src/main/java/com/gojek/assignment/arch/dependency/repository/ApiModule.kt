package com.gojek.assignment.arch.dependency.repository

import com.gojek.data.repo.repository.RepoApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module(includes = [NetworkModule::class])
class ApiModule {

    @Provides
    internal fun providesRepoApi(retrofit: Retrofit): RepoApi {
        return retrofit.create(RepoApi::class.java)
    }
}