package com.example.trafficsignrecognition.features.recognizer.data.datasources

import android.content.Context
import android.speech.tts.TextToSpeech
import com.example.trafficsignrecognition.core.failure.TextToSpeechInitFailure
import com.example.trafficsignrecognition.core.failure.TextToSpeechSpeakFailure
import com.example.trafficsignrecognition.features.recognizer.domain.usecases.InitializeTextToSpeechParams
import com.example.trafficsignrecognition.features.recognizer.domain.usecases.SpeakTextParams
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

interface TextToSpeechDatasource {
    fun initialize(params: InitializeTextToSpeechParams)
    fun speak(params: SpeakTextParams)
    fun shutdown()
}

class TextToSpeechDatasourceImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : TextToSpeechDatasource {
    private var textToSpeech: TextToSpeech? = null

    override fun initialize(params: InitializeTextToSpeechParams) {
        textToSpeech = TextToSpeech(context) {
            if (it != TextToSpeech.SUCCESS) {
                throw TextToSpeechInitFailure()
            }
        }
        textToSpeech!!.language = params.locale
    }

    override fun speak(params: SpeakTextParams) {
        if (textToSpeech == null) {
            throw TextToSpeechInitFailure()
        }

        val result = textToSpeech!!.speak(params.text, TextToSpeech.QUEUE_FLUSH, null, null)
        if (result == TextToSpeech.ERROR) {
            throw TextToSpeechSpeakFailure()
        }
    }

    override fun shutdown() {
        textToSpeech?.shutdown()
    }
}