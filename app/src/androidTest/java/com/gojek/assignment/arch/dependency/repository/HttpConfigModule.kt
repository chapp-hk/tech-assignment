package com.gojek.assignment.arch.dependency.repository

import com.gojek.assignment.BuildConfig
import dagger.Module
import dagger.Provides
import okhttp3.logging.HttpLoggingInterceptor

@Module
class HttpConfigModule {

    @Provides
    internal fun providesBaseUrl(): String {
        return BuildConfig.BASE_URL_TEST
    }

    @Provides
    internal fun providesHttpLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }
}