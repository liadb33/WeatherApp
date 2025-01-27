package com.example.weatherapp.Utilities

import android.content.Context

class SharedPreferencesManagerV3 private constructor(context: Context) {

    private val sharedPref = context.getSharedPreferences("SP_FILE", Context.MODE_PRIVATE)

    companion object {
        @Volatile
        private var instance: SharedPreferencesManagerV3? = null

        fun init(context: Context): SharedPreferencesManagerV3 {
            return instance ?: synchronized(this) {
                instance ?: SharedPreferencesManagerV3(context).also { instance = it }
            }
        }

        fun getInstance(): SharedPreferencesManagerV3 {
            return instance
                ?: throw IllegalStateException("Shared preferences must be initialized by calling init(context) before use.")
        }
    }


    fun putString(key: String, value: String) {
        with(sharedPref.edit()){
            putString(key,value)
            apply()
        }
    }

    fun getString(key: String, defaultValue: String): String {
        return sharedPref.getString(key, defaultValue) ?: defaultValue
    }
}