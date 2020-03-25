package com.gojek.assignment.arch.dependency

import com.gojek.assignment.BuildConfig
import dagger.Module
import dagger.Provides

@Module
class TestBaseUrlModule {

    @Provides
    internal fun providesBaseUrl(): String {
        return BuildConfig.BASE_URL_TEST
    }
}