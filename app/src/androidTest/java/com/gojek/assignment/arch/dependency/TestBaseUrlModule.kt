package com.gojek.assignment.arch.dependency

import com.gojek.assignment.BuildConfig
import dagger.Module
import dagger.Provides

@Module
class TestBaseUrlModule {

    @Provides
    internal fun providesBaseUrl(): String {
        return "http://localhost:8080/"
    }
}