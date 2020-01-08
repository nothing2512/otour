package com.example.otour.utils

import android.content.Context

class MainPreference(context: Context) {

    private val preferences = context.getSharedPreferences(Constants.PREFERENCES, Constants.PRIVATE)
    private val editor = preferences.edit()

    val isLogin: Boolean
        get() = getBoolean(Constants.LOGIN_KEY)

    fun set(key: String, value: Any?) {
        when (value) {
            is String -> editor.putString(key, value)
            is Int -> editor.putInt(key, value)
            is Float -> editor.putFloat(key, value)
            is Long -> editor.putLong(key, value)
            is Boolean -> editor.putBoolean(key, value)
        }
        editor.apply()
    }

    fun clear() {
        editor.clear()
        editor.apply()
    }

    fun getString(key: String) = preferences.getString(key, "") ?: ""

    fun getInt(key: String) = preferences.getInt(key, 0)

    fun getFloat(key: String) = preferences.getFloat(key, 0f)

    fun getLong(key: String) = preferences.getLong(key, 0L)

    fun getBoolean(key: String) = preferences.getBoolean(key, false)

    fun login() = set(Constants.LOGIN_KEY, true)

    fun logout() = set(Constants.LOGIN_KEY, false)
}