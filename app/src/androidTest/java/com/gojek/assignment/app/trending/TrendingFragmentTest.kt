package com.gojek.assignment.app.trending

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import com.gojek.assignment.R
import com.gojek.assignment.app.MainActivity
import com.gojek.assignment.arch.dispatcher.ErrorDispatcher
import com.gojek.assignment.arch.dispatcher.RepoDispatcher
import com.gojek.assignment.arch.fragment.AbstractFragmentTest
import com.gojek.assignment.arch.matcher.RecyclerViewMatcher.atPosition
import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.not
import org.junit.Rule
import org.junit.Test

@LargeTest
class TrendingFragmentTest : AbstractFragmentTest() {

    @get:Rule
    val activityRule = ActivityTestRule(MainActivity::class.java, true, false)

    @Test
    fun should_show_error_view_when_network_error() {
        mockWebServer.setDispatcher(ErrorDispatcher())

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
        mockWebServer.setDispatcher(ErrorDispatcher())

        activityRule.launchActivity(null)

        onView(withId(R.id.btRetry)).check(matches(isDisplayed()))

        Thread.sleep(1000)

        mockWebServer.setDispatcher(RepoDispatcher())

        onView(withId(R.id.btRetry)).perform(click())

        onView(withId(R.id.recyclerView)).check(matches(hasMinimumChildCount(1)))
    }

    @Test
    fun should_toggle_expand_state_when_click_on_item() {
        mockWebServer.setDispatcher(RepoDispatcher())

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