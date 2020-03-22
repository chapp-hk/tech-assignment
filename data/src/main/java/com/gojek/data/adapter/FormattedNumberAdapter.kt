package com.gojek.data.adapter

import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson
import java.text.NumberFormat

class FormattedNumberAdapter(
    private val numberFormat: NumberFormat
) {

    @ToJson
    fun toJson(@FormattedNumber value: String): Int {
        return value.toInt()
    }

    @FromJson
    @FormattedNumber
    fun fromJson(value: Int): String {
        return numberFormat.format(value)
    }
}