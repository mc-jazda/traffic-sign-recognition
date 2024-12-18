package com.example.trafficsignrecognition.features.recognizer.domain.viewmodels

import androidx.lifecycle.ViewModel
import com.example.trafficsignrecognition.core.usecase.NoParams
import com.example.trafficsignrecognition.core.usecase.UseCaseResultFailure
import com.example.trafficsignrecognition.features.recognizer.domain.usecases.InitializeTextToSpeechParams
import com.example.trafficsignrecognition.features.recognizer.domain.usecases.InitializeTextToSpeechUseCase
import com.example.trafficsignrecognition.features.recognizer.domain.usecases.ShutdownTextToSpeechUseCase
import com.example.trafficsignrecognition.features.recognizer.domain.usecases.SpeakTextParams
import com.example.trafficsignrecognition.features.recognizer.domain.usecases.SpeakTextUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class TextToSpeechViewModel @Inject constructor(
    initializeTextToSpeechUseCase: InitializeTextToSpeechUseCase,
    private val speakTextUseCase: SpeakTextUseCase,
    private val shutdownTextToSpeech: ShutdownTextToSpeechUseCase
) : ViewModel() {
    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> get() = _errorMessage

    init {
        initializeTextToSpeechUseCase(
            InitializeTextToSpeechParams(locale = Locale.US)
        )
    }

    fun speak(text: String) {
        val result = speakTextUseCase(SpeakTextParams(text))

        if (result is UseCaseResultFailure) {
            _errorMessage.value = result.failure.errorMessage
        }
    }

    override fun onCleared() {
        super.onCleared()
        shutdownTextToSpeech(NoParams())
    }
}