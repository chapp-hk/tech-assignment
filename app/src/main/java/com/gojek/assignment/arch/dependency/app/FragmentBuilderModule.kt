package com.gojek.assignment.arch.dependency.app

import com.gojek.assignment.app.trending.TrendingFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module(includes = [])
abstract class FragmentBuilderModule {

    @ContributesAndroidInjector
    @FragmentScope
    internal abstract fun contributeTrendingFragment(): TrendingFragment
}