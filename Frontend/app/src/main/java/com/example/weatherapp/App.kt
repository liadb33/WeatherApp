package com.example.weatherapp

import android.app.Application
import com.example.weatherapp.Utilities.LocationManager
import com.example.weatherapp.Utilities.SharedPreferencesManagerV3

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        SharedPreferencesManagerV3.init(this)
        LocationManager.init(this)
    }
}