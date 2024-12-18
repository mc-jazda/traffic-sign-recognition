package com.example.trafficsignrecognition.features.recognizer.data.repositories

import com.example.trafficsignrecognition.core.failure.ErrorHandler
import com.example.trafficsignrecognition.core.failure.TextToSpeechInitFailure
import com.example.trafficsignrecognition.core.failure.TextToSpeechSpeakFailure
import com.example.trafficsignrecognition.core.usecase.UseCaseResult
import com.example.trafficsignrecognition.features.recognizer.data.datasources.TextToSpeechDatasource
import com.example.trafficsignrecognition.features.recognizer.domain.repositories.TextToSpeechRepository
import com.example.trafficsignrecognition.features.recognizer.domain.usecases.InitializeTextToSpeechParams
import com.example.trafficsignrecognition.features.recognizer.domain.usecases.SpeakTextParams
import javax.inject.Inject

class TextToSpeechRepositoryImpl @Inject constructor(
    private val datasource: TextToSpeechDatasource,
    private val errorHandler: ErrorHandler,
) :
    TextToSpeechRepository {
    override fun initialize(params: InitializeTextToSpeechParams): UseCaseResult<Unit> {
        try {
            datasource.initialize(params)
            return UseCaseResult.success()

        } catch (e: Exception) {
            return UseCaseResult.error(
                errorHandler.handleError(
                    error = e,
                    defaultFailure = TextToSpeechInitFailure()
                )
            )
        }
    }

    override fun speak(params: SpeakTextParams): UseCaseResult<Unit> {
        try {
            datasource.speak(params)
            return UseCaseResult.success()

        } catch (e: Exception) {
            return UseCaseResult.error(
                errorHandler.handleError(
                    error = e,
                    defaultFailure = TextToSpeechSpeakFailure()
                )
            )
        }
    }

    override fun shutdown(): UseCaseResult<Unit> {
        try {
            datasource.shutdown()
            return UseCaseResult.success()
        } catch (e: Exception) {
            return UseCaseResult.error(
                errorHandler.handleError(
                    error = e,
                    defaultFailure = TextToSpeechSpeakFailure()
                )
            )
        }
    }
}