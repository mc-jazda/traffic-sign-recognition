package com.example.trafficsignrecognition.features.recognizer.domain.usecases

import com.example.trafficsignrecognition.core.usecase.UseCase
import com.example.trafficsignrecognition.core.usecase.UseCaseResult
import com.example.trafficsignrecognition.features.recognizer.domain.repositories.TextToSpeechRepository
import java.util.Locale
import javax.inject.Inject

class InitializeTextToSpeechUseCase @Inject constructor(
    private val repository: TextToSpeechRepository
) : UseCase<Unit, InitializeTextToSpeechParams>() {
    override fun invoke(params: InitializeTextToSpeechParams): UseCaseResult<Unit> {
        return repository.initialize(params)
    }
}

data class InitializeTextToSpeechParams(val locale: Locale)