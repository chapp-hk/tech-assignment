package com.gojek.assignment.arch.dependency.repository

import android.app.Application
import androidx.room.Room
import com.gojek.assignment.arch.helper.DaoProvider
import com.gojek.data.repo.repository.RepoDao
import dagger.Module
import dagger.Provides

@Module
class DaoModule {

    private val databaseName = "DaoProvider.db"

    @Provides
    internal fun providesDatabase(application: Application): DaoProvider {
        return Room.databaseBuilder(
            application.applicationContext,
            DaoProvider::class.java, databaseName
        ).build()
    }

    @Provides
    internal fun providesCoinCartDao(daoProvider: DaoProvider): RepoDao {
        return daoProvider.getRepoDao()
    }
}