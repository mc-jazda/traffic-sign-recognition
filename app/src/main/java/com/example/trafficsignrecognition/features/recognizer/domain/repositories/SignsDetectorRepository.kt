package com.example.trafficsignrecognition.features.recognizer.domain.repositories

import com.example.trafficsignrecognition.core.usecase.UseCaseResult
import com.example.trafficsignrecognition.features.recognizer.domain.entities.SignDetectorResult
import com.example.trafficsignrecognition.features.recognizer.domain.usecases.DetectSignsParams

interface SignsDetectorRepository {
    suspend fun detectTrafficSigns(params: DetectSignsParams): UseCaseResult<SignDetectorResult>
    fun setupDetector(): UseCaseResult<Unit>
    fun clearDetector(): UseCaseResult<Unit>
}