package com.gojek.assignment.app

import com.gojek.assignment.arch.dependency.DaggerTestAppComponent
import com.gojek.assignment.arch.dependency.TestAppComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

class TestApp : App() {

    lateinit var testAppComponent: TestAppComponent

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        if (!::testAppComponent.isInitialized) {
            testAppComponent = DaggerTestAppComponent.factory().create(this)
        }

        return testAppComponent
    }
}