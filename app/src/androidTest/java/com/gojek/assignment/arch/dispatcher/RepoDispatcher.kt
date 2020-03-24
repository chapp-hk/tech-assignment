package com.gojek.assignment.arch.dispatcher

import androidx.test.platform.app.InstrumentationRegistry
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.RecordedRequest

class RepoDispatcher : Dispatcher() {

    override fun dispatch(request: RecordedRequest): MockResponse {
        return MockResponse().setBody(getAssetString("repositories.json"))
    }

    private fun getAssetString(fileName: String): String {
        return InstrumentationRegistry.getInstrumentation()
            .context.assets.open(fileName).bufferedReader().use { it.readText() }
    }
}