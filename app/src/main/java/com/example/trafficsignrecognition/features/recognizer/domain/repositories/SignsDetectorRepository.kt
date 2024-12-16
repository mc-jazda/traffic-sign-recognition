package com.example.trafficsignrecognition.features.recognizer.domain.repositories

import com.example.trafficsignrecognition.core.usecase.UseCaseResult
import com.example.trafficsignrecognition.features.recognizer.domain.entities.SignDetectorResult
import com.example.trafficsignrecognition.features.recognizer.domain.usecases.DetectSignsParams
import kotlinx.coroutines.flow.Flow

interface SignsDetectorRepository {
    suspend fun detectTrafficSigns(params: DetectSignsParams): Flow<UseCaseResult<SignDetectorResult>>
    fun setupDetector(): UseCaseResult<Unit>
    fun clearDetector(): UseCaseResult<Unit>
}