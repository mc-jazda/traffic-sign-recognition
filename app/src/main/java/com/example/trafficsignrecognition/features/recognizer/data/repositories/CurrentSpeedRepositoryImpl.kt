package com.example.trafficsignrecognition.features.recognizer.data.repositories

import com.example.trafficsignrecognition.core.failure.ErrorHandler
import com.example.trafficsignrecognition.core.failure.FetchLocationFailure
import com.example.trafficsignrecognition.core.usecase.UseCaseResult
import com.example.trafficsignrecognition.features.recognizer.data.datasources.CurrentSpeedDataSource
import com.example.trafficsignrecognition.features.recognizer.domain.repositories.CurrentSpeedRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CurrentSpeedRepositoryImpl @Inject constructor(
    private val datasource: CurrentSpeedDataSource,
    private val errorHandler: ErrorHandler,
) : CurrentSpeedRepository {
    override suspend fun getCurrentSpeed(): Flow<UseCaseResult<Float>> {
        return datasource.getCurrentSpeedFlow()
            .map { result ->
                UseCaseResult.success(data = result)
            }
            .catch { error ->
                val defaultFailure = FetchLocationFailure().apply { description = error.message }
                emit(
                    UseCaseResult.error(
                        error = errorHandler.handleError(
                            error = error,
                            defaultFailure = defaultFailure
                        )
                    )
                )
            }
    }
}