package com.deva.myapplication.ui

import android.util.Log
import java.util.concurrent.TimeUnit

object PerfTrack {
    var millis = 0L
    var name = ""
    fun startTrack(name: String) {
        this.millis = System.currentTimeMillis()
        Log.d("PerformanceMonitoring start", PerfTrack.name +": "+ millis)
        this.name = name
    }

    fun stopTrack() {
        var diff = System.currentTimeMillis() - millis
        Log.d("PerformanceMonitoring", name+": "+diff)

        this.millis = 0L
        this.name = ""
    }
}