package com.example.trafficsignrecognition.features.recognizer.domain.usecases

import com.example.trafficsignrecognition.core.usecase.AsyncUseCase
import com.example.trafficsignrecognition.core.usecase.NoParams
import com.example.trafficsignrecognition.core.usecase.UseCaseResult
import com.example.trafficsignrecognition.features.recognizer.domain.repositories.CurrentSpeedRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetCurrentSpeedUseCase @Inject constructor(
    private val repository: CurrentSpeedRepository,
) : AsyncUseCase<Float, NoParams>() {
    override suspend fun invoke(params: NoParams): Flow<UseCaseResult<Float>> =
        withContext(Dispatchers.Default) {
            repository.getCurrentSpeed()
        }
}