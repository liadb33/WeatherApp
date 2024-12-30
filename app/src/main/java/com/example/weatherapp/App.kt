package com.example.weatherapp

import android.app.Application
import com.example.weatherapp.Utilities.LocationManager

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        LocationManager.init(this)
    }
}