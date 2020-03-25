package com.gojek.assignment.arch.dependency.app

import com.gojek.assignment.app.trending.view.TrendingFragment
import com.gojek.assignment.arch.dependency.repository.UseCaseModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module(includes = [UseCaseModule::class])
abstract class FragmentBuilderModule {

    @ContributesAndroidInjector(modules = [UseCaseModule::class])
    @FragmentScope
    internal abstract fun contributeTrendingFragment(): TrendingFragment
}