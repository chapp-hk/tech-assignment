package com.gojek.assignment.arch.dependency

import android.app.Application
import com.gojek.assignment.arch.dependency.app.AppModule
import com.gojek.assignment.arch.dependency.viewmodel.ViewModelBuilderModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

@Component(
    modules = [
        AndroidInjectionModule::class,
        AppModule::class,
        ViewModelBuilderModule::class
    ]
)
interface AppComponent : AndroidInjector<DaggerApplication> {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance application: Application): AppComponent
    }
}