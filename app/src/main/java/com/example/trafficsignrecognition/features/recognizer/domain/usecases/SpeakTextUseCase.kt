package com.example.trafficsignrecognition.features.recognizer.domain.usecases

import com.example.trafficsignrecognition.core.usecase.UseCase
import com.example.trafficsignrecognition.core.usecase.UseCaseResult
import com.example.trafficsignrecognition.features.recognizer.domain.repositories.TextToSpeechRepository
import javax.inject.Inject

class SpeakTextUseCase @Inject constructor(
    private val repository: TextToSpeechRepository
) :
    UseCase<Unit, SpeakTextParams>() {
    override fun invoke(params: SpeakTextParams): UseCaseResult<Unit> {
        return repository.speak(params)
    }
}

data class SpeakTextParams(val text: String)