package com.antz.odometer

import android.app.Service
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Criteria
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Binder
import android.os.Bundle
import android.os.IBinder
import android.support.v4.content.ContextCompat

class OdometerService : Service(), LocationListener {
    val binder = OdometerBinder()

    private var distanceInMeters : Float = 0F
    private var lastLocation : Location? = null
    private var locManager : LocationManager? = null
    override fun onBind(intent: Intent): IBinder = binder

    inner class OdometerBinder : Binder() {

        fun getOdometer() : OdometerService {
            return this@OdometerService
        }
    }

    override fun onLocationChanged(currentLocation: Location?) {
        if (lastLocation == null) {
            lastLocation = currentLocation
        }
        this.distanceInMeters += currentLocation!!.distanceTo(lastLocation)
        lastLocation = currentLocation
    }

    override fun onStatusChanged(p0: String?, p1: Int, p2: Bundle?) {
    }

    override fun onProviderEnabled(p0: String?) {
    }

    override fun onProviderDisabled(p0: String?) {
    }

    override fun onCreate() {
        super.onCreate()

        locManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        if (ContextCompat.checkSelfPermission(this, PERMISSION_STRING) ==
                PackageManager.PERMISSION_GRANTED) {
            val bestProvider = locManager!!.getBestProvider(Criteria(), true)
            if (bestProvider != null) {
                locManager!!.requestLocationUpdates(bestProvider, 1000L, 1.0F, this)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (locManager != null) {
            if (ContextCompat.checkSelfPermission(this, PERMISSION_STRING) ==
                    PackageManager.PERMISSION_GRANTED) {
                locManager!!.removeUpdates(this)
                locManager = null
            }
        }
        locManager!!.removeUpdates(this)
    }

    fun getDistance(): Double {
        return this.distanceInMeters/1609.344
    }

    companion object {
        const val PERMISSION_STRING = android.Manifest.permission.ACCESS_FINE_LOCATION
    }
}


