package com.example.trafficsignrecognition.features.recognizer.data.repositories

import com.example.trafficsignrecognition.core.failure.SignDetectorClearingFailure
import com.example.trafficsignrecognition.core.failure.SignDetectorDetectingFailure
import com.example.trafficsignrecognition.core.failure.SignDetectorSetupFailure
import com.example.trafficsignrecognition.core.failure.ErrorHandler
import com.example.trafficsignrecognition.core.usecase.UseCaseResult
import com.example.trafficsignrecognition.features.recognizer.data.datasources.SignsDetectorDatasource
import com.example.trafficsignrecognition.features.recognizer.domain.entities.SignDetectorResult
import com.example.trafficsignrecognition.features.recognizer.domain.repositories.SignsDetectorRepository
import com.example.trafficsignrecognition.features.recognizer.domain.usecases.DetectSignsParams
import javax.inject.Inject

class SignsDetectorRepositoryImpl @Inject constructor(
    private val datasource: SignsDetectorDatasource,
    private val errorHandler: ErrorHandler,
) : SignsDetectorRepository {

    override suspend fun detectTrafficSigns(params: DetectSignsParams): UseCaseResult<SignDetectorResult> {
        try {
            val result = datasource.detectTrafficSigns(params)
            return UseCaseResult.success(data = result)

        } catch (error: Exception) {
            val defaultFailure = SignDetectorDetectingFailure()
            defaultFailure.description = error.message
            
            return UseCaseResult.error(
                error = errorHandler.handleError(
                    error = error,
                    defaultFailure = defaultFailure
                )
            )
        }
    }

    override fun setupDetector(): UseCaseResult<Unit> {
        try {
            datasource.setupDetector()
            return UseCaseResult.success()

        } catch (error: Exception) {
            return UseCaseResult.error(
                error = errorHandler.handleError(
                    error = error,
                    defaultFailure = SignDetectorSetupFailure()
                )
            )
        }
    }

    override fun clearDetector(): UseCaseResult<Unit> {
        try {
            datasource.clearDetector()
            return UseCaseResult.success()

        } catch (error: Exception) {
            return UseCaseResult.error(
                error = errorHandler.handleError(
                    error = error,
                    defaultFailure = SignDetectorClearingFailure()
                )
            )
        }
    }
}