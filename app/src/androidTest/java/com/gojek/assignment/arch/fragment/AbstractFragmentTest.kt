package com.gojek.assignment.arch.fragment

import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import com.gojek.assignment.app.TestApp
import com.gojek.assignment.arch.idlingresource.OkHttpIdlingResource
import com.gojek.assignment.arch.dependency.TestAppComponent
import com.gojek.assignment.arch.helper.DaoProvider
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import javax.inject.Inject

abstract class AbstractFragmentTest {

    @Inject
    protected lateinit var okHttpIdlingResource: OkHttpIdlingResource

    protected val mockWebServer = MockWebServer()

    @Before
    open fun setUp() {
        clearLocalDatabase()
        mockWebServer.start(8080)
    }

    @After
    open fun tearDown() {
        mockWebServer.shutdown()
    }

    protected fun getTestAppComponent(): TestAppComponent {
        return (InstrumentationRegistry.getInstrumentation().targetContext.applicationContext as TestApp).testAppComponent
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