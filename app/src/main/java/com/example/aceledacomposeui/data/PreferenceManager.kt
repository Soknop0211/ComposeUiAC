package com.example.aceledacomposeui.data

import android.content.Context
import com.example.aceledacomposeui.utils.Constants

object PreferenceManager {
    fun saveString(key: String?, value: String?, activity: Context) {
        val aSharedPreferences =
            activity.getSharedPreferences(Constants.KEY_PREFERENCE, Context.MODE_PRIVATE)
        val aSharedPreferencesEdit = aSharedPreferences.edit()
        aSharedPreferencesEdit.putString(key, value)
        aSharedPreferencesEdit.apply()
    }

    fun getString(key: String?, activity: Context): String? {
        val aSharedPreferences =
            activity.getSharedPreferences(Constants.KEY_PREFERENCE, Context.MODE_PRIVATE)
        return aSharedPreferences.getString(key, "")
    }

    fun saveBoolean(key: String?, value: Boolean, activity: Context) {
        val aSharedPreferences =
            activity.getSharedPreferences(Constants.KEY_PREFERENCE, Context.MODE_PRIVATE)
        val aSharedPreferencesEdit = aSharedPreferences.edit()
        aSharedPreferencesEdit.putBoolean(key, value)
        aSharedPreferencesEdit.apply()
    }

    fun getBoolean(key: String?, activity: Context): Boolean {
        val aSharedPreferences =
            activity.getSharedPreferences(Constants.KEY_PREFERENCE, Context.MODE_PRIVATE)
        return aSharedPreferences.getBoolean(key, false)
    }

    fun clear(activity: Context) {
        val preferences =
            activity.getSharedPreferences(Constants.KEY_PREFERENCE, Context.MODE_PRIVATE)
        val editor = preferences.edit()
        editor.clear()
        editor.apply()
    }
}