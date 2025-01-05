package com.example.weatherapp.Utilities

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.os.Looper
import android.util.Log
import androidx.core.app.ActivityCompat
import com.example.weatherapp.Interfaces.LocationUpdateCallback
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices

class LocationManager private constructor(context : Context){
    private var appContext : Context = context.applicationContext

    companion object{
        @Volatile
        private var instance : LocationManager? = null

        fun init(context: Context) : LocationManager{
            return instance ?: synchronized(this){
                instance ?: LocationManager(context).also { instance = it }
            }
        }
        
        fun getInstance() : LocationManager{
            return instance ?: throw IllegalStateException("LocationManager must be initialized by calling init(context) before use.\n")
        }

    }

    private var fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(appContext)
    var locationUpdateCallback: LocationUpdateCallback? = null
    private lateinit var locationCallback : LocationCallback
    private var latitude : Double = 0.0
    private var longitude : Double = 0.0


    fun getDeviceLocation(){

        if (ActivityCompat.checkSelfPermission(appContext, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            val locationRequest = LocationRequest.create()
            locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
            locationRequest.setInterval(10000) // 10 seconds
            locationRequest.setFastestInterval(5000) // 5 seconds

            locationCallback = object : LocationCallback() {
                override fun onLocationResult(locationResult: LocationResult) {
                    if (locationResult != null && locationResult.locations.size > 0) {
                        val index = locationResult.locations.size - 1
                        latitude = locationResult.locations[index].latitude
                        longitude = locationResult.locations[index].longitude
                        Log.d("location", "onLocationResult: lat:$latitude lon:$longitude")

                        locationUpdateCallback?.onLocationUpdated(latitude,longitude)
                        stopLocationUpdates()
                    }
                }
            }
            fusedLocationProviderClient.requestLocationUpdates(
                locationRequest,
                locationCallback,
                Looper.getMainLooper()
            )
        } else{
            requestLocationPermission(appContext)
        }
    }

    fun getLastKnownLocation() {
        if (ActivityCompat.checkSelfPermission(appContext,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                appContext,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
           fusedLocationProviderClient.lastLocation.addOnSuccessListener {
               if (it !== null) {
                   latitude = it.latitude
                   longitude = it.longitude
                   Log.d("location", "getLastKnownLocation: lat:$latitude lon:$longitude")
                   locationUpdateCallback?.onLocationUpdated(latitude, longitude)

               }
           }
        }
    }

    fun requestLocationPermission(context: Context) {
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if(context is Activity)
                ActivityCompat.requestPermissions(context as Activity, arrayOf<String>(Manifest.permission.ACCESS_FINE_LOCATION), 1)
            else
                Log.e("LocationManager", "Location permission not granted, might not be an activity")
        }
    }

    fun stopLocationUpdates(){
        if(this::locationCallback.isInitialized && fusedLocationProviderClient != null)
            fusedLocationProviderClient.removeLocationUpdates(locationCallback)
    }

}