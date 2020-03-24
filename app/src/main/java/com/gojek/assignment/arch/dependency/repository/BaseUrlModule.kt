package com.gojek.assignment.arch.dependency.repository

import com.gojek.assignment.BuildConfig
import dagger.Module
import dagger.Provides

@Module
class BaseUrlModule {

    @Provides
    internal fun providesBaseUrl(): String {
        return BuildConfig.BASE_URL
    }
}