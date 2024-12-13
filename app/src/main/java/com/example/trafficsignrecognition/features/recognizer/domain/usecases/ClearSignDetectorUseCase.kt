package com.example.trafficsignrecognition.features.recognizer.domain.usecases

import com.example.trafficsignrecognition.core.usecase.NoParams
import com.example.trafficsignrecognition.core.usecase.UseCase
import com.example.trafficsignrecognition.core.usecase.UseCaseResult
import com.example.trafficsignrecognition.features.recognizer.domain.repositories.SignsDetectorRepository
import javax.inject.Inject

class ClearSignDetectorUseCase @Inject constructor(
    private val repository: SignsDetectorRepository,
) : UseCase<Unit, NoParams>() {
    override fun invoke(params: NoParams): UseCaseResult<Unit> {
        return repository.clearDetector()
    }
}