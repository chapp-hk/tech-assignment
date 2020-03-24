package com.gojek.assignment.arch.helper

import android.content.SharedPreferences
import com.gojek.data.local.IAppPreference
import javax.inject.Inject

class AppPreference
@Inject constructor(
    private val sharedPreferences: SharedPreferences
) : IAppPreference {

    private val keyDataTime = "KEY_DATA_TIME"
    private val dataExpirePeriod = 7200000L

    override fun setDataTime(time: Long) {
        sharedPreferences.edit().putLong(keyDataTime, time).apply()
    }

    override fun isDataExpired(): Boolean {
        return System.currentTimeMillis() - sharedPreferences.getLong(keyDataTime, 0) > dataExpirePeriod
    }
}