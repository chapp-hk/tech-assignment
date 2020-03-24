package com.gojek.assignment.arch.dispatcher

import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.RecordedRequest

class ErrorDispatcher : Dispatcher() {

    override fun dispatch(request: RecordedRequest): MockResponse {
        return MockResponse().setResponseCode(500).setBody("")
    }
}