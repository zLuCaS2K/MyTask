package com.lucasprojects.mytask.util

import android.content.Context
import android.content.SharedPreferences

class SecurityPreferences(context: Context) {

    private val mSecurityPreferences: SharedPreferences = context.getSharedPreferences("Tasks", Context.MODE_PRIVATE)

    fun setSharedStored(key: String, value: String) {
        this.mSecurityPreferences.edit().putString(key, value).apply()
    }

    fun getSharedStored(key: String): String {
        return mSecurityPreferences.getString(key, "").toString()
    }

    fun removeSharedStored(key: String) {
        this.mSecurityPreferences.edit().remove(key).apply()
    }

}