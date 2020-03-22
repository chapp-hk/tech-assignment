package com.gojek.assignment.app

import com.facebook.drawee.backends.pipeline.Fresco
import com.facebook.imagepipeline.core.ImagePipelineConfig
import com.gojek.assignment.arch.dependency.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication


class App : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.factory().create(this)
    }

    override fun onCreate() {
        super.onCreate()
        initSdk()
    }

    private fun initSdk() {
        val config = ImagePipelineConfig.newBuilder(this)
            .setDownsampleEnabled(true) // enable downsample to lower memory usage
            .build()

        Fresco.initialize(this, config)
    }
}