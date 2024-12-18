package com.example.trafficsignrecognition.features.recognizer.domain.repositories

import com.example.trafficsignrecognition.core.usecase.UseCaseResult
import com.example.trafficsignrecognition.features.recognizer.domain.usecases.InitializeTextToSpeechParams
import com.example.trafficsignrecognition.features.recognizer.domain.usecases.SpeakTextParams

interface TextToSpeechRepository {
    fun initialize(params: InitializeTextToSpeechParams): UseCaseResult<Unit>
    fun speak(params: SpeakTextParams): UseCaseResult<Unit>
    fun shutdown(): UseCaseResult<Unit>

}