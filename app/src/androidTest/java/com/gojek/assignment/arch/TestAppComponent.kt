package com.gojek.assignment.arch

import android.app.Application
import com.gojek.assignment.arch.dependency.app.AppModule
import com.gojek.assignment.arch.dependency.app.AppScope
import com.gojek.assignment.arch.dependency.app.FragmentBuilderModule
import com.gojek.assignment.arch.dependency.viewmodel.ViewModelBuilderModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

@AppScope
@Component(
    modules = [
        AndroidInjectionModule::class,
        AppModule::class,
        FragmentBuilderModule::class,
        ViewModelBuilderModule::class
    ]
)
interface TestAppComponent : AndroidInjector<DaggerApplication> {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance application: Application): TestAppComponent
    }
}