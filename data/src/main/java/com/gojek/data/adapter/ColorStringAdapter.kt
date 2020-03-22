package com.gojek.data.adapter

import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson

class ColorStringAdapter(
    private val colorHelper: IColorHelper
) {

    @ToJson
    fun toJson(@ColorString value: Int): String {
        return value.toString(16)
    }

    @FromJson
    @ColorString
    fun fromJson(value: String): Int {
        return colorHelper.parseColor(value)
    }
}