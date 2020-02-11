package com.lucasprojects.mytask.util

import android.content.Context
import android.content.SharedPreferences
import android.util.Log

class SecurityPreferences(context: Context) {

    /** Implementação do SharedPreferences */
    private val mSecurityPreferences: SharedPreferences =
        context.getSharedPreferences("Tasks", Context.MODE_PRIVATE)

    /** Salvando no SharedPreferences */
    fun setSharedStored(key: String, value: String) {
        this.mSecurityPreferences.edit().putString(key, value).apply()
        Log.v("SecurityPreferences", "$key e $value")
    }

    /** Recuperando no SharedPreferences */
    fun getSharedStored(key: String): String {
        return mSecurityPreferences.getString(key, "").toString()
    }

    /** Removendo do SharedPreferences */
    fun removeSharedStored(key: String) {
        this.mSecurityPreferences.edit().remove(key).apply()
    }

}