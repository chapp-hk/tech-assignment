package com.gojek.assignment.arch.helper

import android.content.SharedPreferences
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.verifySequence
import org.junit.Before
import org.junit.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class AppPreferenceTest {

    @MockK
    private lateinit var sharedPreferences: SharedPreferences

    @MockK
    private lateinit var editor: SharedPreferences.Editor

    private lateinit var appPreference: AppPreference

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        appPreference = AppPreference(sharedPreferences)
    }

    @Test
    fun setDataTime() {
        val time = System.currentTimeMillis()
        every { sharedPreferences.edit() } returns editor
        every { editor.putLong(any(), any()) } returns editor
        every { editor.apply() } returns Unit

        appPreference.setDataTime(time)

        verifySequence {
            sharedPreferences.edit()
            editor.putLong(any(), eq(time))
            editor.apply()
        }
    }

    @Test
    fun `isDataExpired should return false when time in preference not exceed expire period`() {
        every { sharedPreferences.getLong(any(), 0) } returns System.currentTimeMillis()

        assertFalse(appPreference.isDataExpired())
    }

    @Test
    fun `isDataExpired should return true when time in preference exceeded expire period`() {
        every { sharedPreferences.getLong(any(), 0) } returns 10000L

        assertTrue(appPreference.isDataExpired())
    }
}