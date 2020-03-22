package com.gojek.assignment.arch.dependency.repository

import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.annotations.SchedulerSupport.CUSTOM
import io.reactivex.annotations.SchedulerSupport.IO
import io.reactivex.schedulers.Schedulers
import javax.inject.Named

@Module
class RxModule {

    @Provides
    @Named(CUSTOM)
    fun provideAndroidScheduler(): Scheduler {
        return AndroidSchedulers.mainThread()
    }

    @Provides
    @Named(IO)
    fun provideIoScheduler(): Scheduler {
        return Schedulers.io()
    }
}