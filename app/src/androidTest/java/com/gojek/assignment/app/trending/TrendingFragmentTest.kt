package com.gojek.assignment.app.trending

import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import com.gojek.assignment.R
import com.gojek.assignment.app.MainActivity
import com.gojek.assignment.arch.dispatcher.ErrorDispatcher
import com.gojek.assignment.arch.dispatcher.RepoDispatcher
import com.gojek.assignment.arch.helper.DaoProvider
import com.gojek.assignment.arch.matcher.RecyclerViewMatcher.atPosition
import okhttp3.mockwebserver.MockWebServer
import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.not
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@LargeTest
class TrendingFragmentTest {

    @get:Rule
    val activityRule = ActivityTestRule(MainActivity::class.java, true, false)

    private val mockWebServer = MockWebServer()

    @Before
    fun setUp() {
        Room.databaseBuilder(
            InstrumentationRegistry.getInstrumentation().targetContext.applicationContext,
            DaoProvider::class.java,
            "DaoProvider.db"
        ).build().apply {
            getRepoDao().deleteAllRepos()
        }
        mockWebServer.start(8080)
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun should_show_error_view_when_network_error() {
        mockWebServer.dispatcher = ErrorDispatcher()

        activityRule.launchActivity(null)

        onView(withId(R.id.ivNetworkError)).check(matches(isDisplayed()))

        onView(
            allOf(
                withId(R.id.tvTitle),
                withText(R.string.title_something_went_wrong)
            )
        ).check(matches(isDisplayed()))

        onView(
            allOf(
                withId(R.id.tvSubitle),
                withText(R.string.title_block_signal)
            )
        ).check(matches(isDisplayed()))

        onView(
            allOf(
                withId(R.id.btRetry),
                withText(R.string.button_retry)
            )
        ).check(matches(isDisplayed()))
    }

    @Test
    fun should_refresh_when_click_retry_button() {
        mockWebServer.dispatcher = ErrorDispatcher()

        activityRule.launchActivity(null)

        onView(withId(R.id.btRetry)).check(matches(isDisplayed()))

        Thread.sleep(1000)

        mockWebServer.dispatcher = RepoDispatcher()

        onView(withId(R.id.btRetry)).perform(click())

        onView(withId(R.id.recyclerView)).check(matches(hasMinimumChildCount(1)))
    }

    @Test
    fun should_toggle_expand_state_when_click_on_item() {
        mockWebServer.dispatcher = RepoDispatcher()

        activityRule.launchActivity(null)

        onView(withId(R.id.recyclerView))
            .perform(actionOnItemAtPosition<RecyclerView.ViewHolder>(1, click()))
            .check(
                matches(
                    atPosition(
                        1,
                        hasDescendant(allOf(withId(R.id.tvDescription), isDisplayed()))
                    )
                )
            )
            .perform(actionOnItemAtPosition<RecyclerView.ViewHolder>(1, click()))
            .check(
                matches(
                    atPosition(
                        1,
                        hasDescendant(allOf(withId(R.id.tvDescription), not(isDisplayed())))
                    )
                )
            )
    }
}