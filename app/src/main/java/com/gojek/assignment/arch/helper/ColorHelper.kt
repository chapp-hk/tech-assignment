package com.gojek.assignment.arch.helper

import android.graphics.Color
import com.gojek.data.adapter.IColorHelper
import javax.inject.Inject

class ColorHelper
@Inject constructor() : IColorHelper {

    override fun parseColor(colorString: String): Int {
        return if (colorString.isNotEmpty()) {
            Color.parseColor(colorString)
        } else {
            Color.BLACK
        }
    }
}