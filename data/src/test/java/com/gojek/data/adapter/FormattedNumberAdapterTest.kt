package com.gojek.data.adapter

import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import java.text.NumberFormat

class FormattedNumberAdapterTest {

    @MockK
    private lateinit var numberFormat: NumberFormat

    private lateinit var formattedNumberAdapter: FormattedNumberAdapter

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        formattedNumberAdapter = FormattedNumberAdapter(numberFormat)
    }

    @Test
    fun `toJson should return int value when parameter is valid number string`() {
        val numberString = "6578"

        assertEquals(numberString.toInt(), formattedNumberAdapter.toJson(numberString))
    }

    @Test
    fun `toJson should return 0 when parameter is invalid number string`() {
        val invalidNumberString = "invalid"

        assertEquals(0, formattedNumberAdapter.toJson(invalidNumberString))
    }

    @Test
    fun `fromJson should call number format`() {
        val number = 1000000
        every { numberFormat.format(eq(number)) } returns number.toString()

        formattedNumberAdapter.fromJson(number)

        verify { numberFormat.format(eq(number)) }
    }
}