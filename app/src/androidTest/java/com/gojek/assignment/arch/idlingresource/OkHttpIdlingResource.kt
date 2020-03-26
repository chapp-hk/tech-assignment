package com.gojek.assignment.arch.idlingresource

import androidx.test.espresso.IdlingResource
import androidx.test.espresso.IdlingResource.ResourceCallback
import okhttp3.OkHttpClient
import javax.inject.Inject

class OkHttpIdlingResource
@Inject constructor(
    private val name: String,
    private val okHttpClient: OkHttpClient
) : IdlingResource {

    override fun getName(): String {
        return name
    }

    override fun isIdleNow(): Boolean {
        return okHttpClient.dispatcher().runningCallsCount() == 0
    }

    override fun registerIdleTransitionCallback(callback: ResourceCallback) {
        okHttpClient.dispatcher().setIdleCallback {
            callback.onTransitionToIdle()
        }
    }
}