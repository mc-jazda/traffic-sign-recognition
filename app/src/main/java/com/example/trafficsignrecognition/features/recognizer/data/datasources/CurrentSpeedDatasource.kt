package com.example.trafficsignrecognition.features.recognizer.data.datasources

import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import com.example.trafficsignrecognition.core.failure.LocationNullFailure
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.suspendCancellableCoroutine
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

interface CurrentSpeedDataSource {
    fun getCurrentSpeedFlow(): Flow<Float>
}

class CurrentSpeedDataSourceImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : CurrentSpeedDataSource {

    private val fusedLocationClient: FusedLocationProviderClient =
        LocationServices.getFusedLocationProviderClient(context)

    override fun getCurrentSpeedFlow(): Flow<Float> {
        return flow {
            val location = getLastKnownLocation()
            if (location != null) {
                val speedInKmH = location.speed.times(3.6f) // Convert to km/h
                emit(speedInKmH)
            } else {
                throw LocationNullFailure()
            }
        }
    }

    @SuppressLint("MissingPermission")
    private suspend fun getLastKnownLocation(): Location? {
        return suspendCancellableCoroutine { continuation ->
            fusedLocationClient.lastLocation.addOnSuccessListener { location ->
                    continuation.resume(location)
                }.addOnFailureListener { exception ->
                    continuation.resumeWithException(exception)
                }
        }
    }
}
