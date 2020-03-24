package com.gojek.data.local

interface IAppPreference {

    fun setDataTime(time: Long)

    fun isDataExpired(): Boolean
}