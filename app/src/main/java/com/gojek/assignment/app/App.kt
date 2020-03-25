package com.gojek.assignment.app

import android.content.Context
import androidx.multidex.MultiDex
import com.facebook.drawee.backends.pipeline.Fresco
import com.facebook.imagepipeline.core.ImagePipelineConfig
import com.gojek.assignment.BuildConfig
import com.gojek.assignment.arch.dependency.DaggerAppComponent
import com.gojek.assignment.arch.helper.OpenForTesting
import com.google.android.gms.security.ProviderInstaller
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import timber.log.Timber
import timber.log.Timber.DebugTree

@OpenForTesting
class App : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.factory().create(this)
    }

    override fun onCreate() {
        super.onCreate()
        initTimber()
        initFresco()
        installSslCert()
    }

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

    private fun installSslCert() {
        try {
            ProviderInstaller.installIfNeeded(this)
        } catch (throwable: Throwable) {
            Timber.e(throwable)
        }
    }

    private fun initTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(DebugTree())
        }
    }

    private fun initFresco() {
        val config = ImagePipelineConfig.newBuilder(this)
            .setDownsampleEnabled(true) // enable downsample to lower memory usage
            .build()

        Fresco.initialize(this, config)
    }
}