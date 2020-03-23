package com.gojek.data.adapter

import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

class ColorStringAdapterTest {

    @MockK
    private lateinit var colorHelper: IColorHelper

    private lateinit var colorStringAdapter: ColorStringAdapter

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        colorStringAdapter = ColorStringAdapter(colorHelper)
    }

    @Test
    fun toJson() {
        val value = 6879

        assertEquals(value.toString(16), colorStringAdapter.toJson(value))
    }

    @Test
    fun fromJson() {
        val value = "#200323"
        every { colorHelper.parseColor(any()) } returns 8790

        colorStringAdapter.fromJson(value)

        verify { colorHelper.parseColor(eq(value)) }
    }
}