package com.gojek.assignment.arch.helper

import android.graphics.Color
import io.mockk.every
import io.mockk.mockkStatic
import io.mockk.verify
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

class ColorHelperTest {

    private lateinit var colorHelper: ColorHelper

    @Before
    fun setUp() {
        colorHelper = ColorHelper()
    }

    @Test
    fun `parseColor should return black when color string is empty`() {
        assertEquals(Color.BLACK, colorHelper.parseColor(""))
    }

    @Test
    fun `parseColor should call Color parColor when color string is not empty`() {
        mockkStatic(Color::class)
        every { Color.parseColor(any()) } returns 6789

        val colorString = "#kdjslk"

        colorHelper.parseColor(colorString)

        verify { Color.parseColor(colorString) }
    }
}