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
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SignsDetectorRepositoryImpl @Inject constructor(
    private val datasource: SignsDetectorDatasource,
    private val errorHandler: ErrorHandler,
) : SignsDetectorRepository {

    override suspend fun detectTrafficSigns(params: DetectSignsParams): Flow<UseCaseResult<SignDetectorResult>> {
        return datasource.detectTrafficSigns(params)
            .map { result ->
                UseCaseResult.success(data = result)
            }
            .catch { error ->
                val defaultFailure = SignDetectorDetectingFailure().apply { description = error.message }
                emit(UseCaseResult.error(
                    error = errorHandler.handleError(
                        error = error,
                        defaultFailure = defaultFailure
                    )
                ))
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