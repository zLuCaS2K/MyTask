package com.lucasprojects.mytask.util

import android.content.Context
import android.content.SharedPreferences

class PreferenceManager(context: Context) {

    private val preferences: SharedPreferences
    private val editor: SharedPreferences.Editor

    companion object {
        private const val PRIVATE_MODE = 0
        private const val PREFERENCE_NAME = "configuration"
        private const val FIRST_TIME = "isFirstRunApp"
    }

    init {
        preferences = context.getSharedPreferences(PREFERENCE_NAME, PRIVATE_MODE)
        editor = preferences.edit()
    }

    fun isFirstRun() = preferences.getBoolean(FIRST_TIME, true)
    fun setFirstRun() {
        editor.putBoolean(FIRST_TIME, false).commit()
        editor.commit()
    }
}