package com.example.trafficsignrecognition.features.recognizer.domain.repositories

import com.example.trafficsignrecognition.core.usecase.UseCaseResult
import kotlinx.coroutines.flow.Flow

interface CurrentSpeedRepository {
    suspend fun getCurrentSpeed(): Flow<UseCaseResult<Float>>
}