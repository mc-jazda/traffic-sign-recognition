package com.example.trafficsignrecognition.features.recognizer.domain.usecases

import android.graphics.Bitmap
import com.example.trafficsignrecognition.core.usecase.AsyncUseCase
import com.example.trafficsignrecognition.core.usecase.UseCaseResult
import com.example.trafficsignrecognition.features.recognizer.domain.entities.SignDetectorResult
import com.example.trafficsignrecognition.features.recognizer.domain.repositories.SignsDetectorRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DetectSignsUseCase @Inject constructor(
    private val repository: SignsDetectorRepository,
) : AsyncUseCase<SignDetectorResult, DetectSignsParams>() {
    override suspend fun invoke(params: DetectSignsParams): UseCaseResult<SignDetectorResult> =
        withContext(Dispatchers.Default) {
            repository.detectTrafficSigns(params)
        }
}

data class DetectSignsParams(
    val frame: Bitmap
)