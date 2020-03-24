package com.gojek.assignment.arch.fragment

import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import com.gojek.assignment.arch.helper.DaoProvider
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before

abstract class AbstractFragmentTest {

    protected val mockWebServer = MockWebServer()

    @Before
    fun setUp() {
        clearLocalDatabase()
        mockWebServer.start(8080)
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    private fun clearLocalDatabase() {
        Room.databaseBuilder(
            InstrumentationRegistry.getInstrumentation().targetContext.applicationContext,
            DaoProvider::class.java,
            "DaoProvider.db"
        ).build().apply {
            getRepoDao().deleteAllRepos()
        }
    }
}