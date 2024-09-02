package com.myweather.core

import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.view.inputmethod.InputMethodManager
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient

private const val CELSIUS_UNICODE = "\u2103"
private const val REQUEST_CODE = 101

fun Double.toTemperature(): String {
    return this.toInt().toString().plus(CELSIUS_UNICODE)
}

fun Activity.getWeatherLocation(
    fusedLocationProvider: FusedLocationProviderClient,
    location: (lon: Double, lat: Double) -> Unit
) {
    val task = fusedLocationProvider.lastLocation

    if(ActivityCompat.checkSelfPermission(
            this,
            android.Manifest.permission.ACCESS_FINE_LOCATION
        ) !=
        PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
            this,
            android.Manifest.permission.ACCESS_COARSE_LOCATION
        ) != PackageManager.PERMISSION_GRANTED
    ) {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
            REQUEST_CODE
        )
        return
    }
    task.addOnSuccessListener { coordinate ->
        location(coordinate.longitude, coordinate.latitude)
    }
}

fun Activity.hideKeyboard() {
    val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    val currentFocusView = currentFocus
    if (currentFocusView != null) {
        imm.hideSoftInputFromWindow(currentFocusView.windowToken, 0)
    }
}