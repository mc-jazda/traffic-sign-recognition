package com.example.trafficsignrecognition.features.recognizer.domain.usecases

import com.example.trafficsignrecognition.core.usecase.NoParams
import com.example.trafficsignrecognition.core.usecase.UseCase
import com.example.trafficsignrecognition.core.usecase.UseCaseResult
import com.example.trafficsignrecognition.features.recognizer.domain.repositories.TextToSpeechRepository
import javax.inject.Inject

class ShutdownTextToSpeechUseCase @Inject constructor(
    private val repository: TextToSpeechRepository
) :
    UseCase<Unit, NoParams>() {
    override fun invoke(params: NoParams): UseCaseResult<Unit> {
        return repository.shutdown()
    }
}