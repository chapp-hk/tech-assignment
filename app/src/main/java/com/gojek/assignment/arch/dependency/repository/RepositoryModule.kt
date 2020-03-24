package com.gojek.assignment.arch.dependency.repository

import android.content.Context
import android.content.SharedPreferences
import com.gojek.assignment.arch.helper.AppPreference
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

    private val preferenceDataTimeName = "preference_data_time"

    @Provides
    internal fun providesSharedPreference(context: Context): SharedPreferences {
        return context.getSharedPreferences(preferenceDataTimeName, Context.MODE_PRIVATE)
    }

    @Provides
    internal fun providesRepoRepository(
        repoApi: RepoApi,
        repoDao: RepoDao,
        appPreference: AppPreference
    ): IRepoRepository {
        return RepoRepository(repoApi, repoDao, appPreference)
    }
}