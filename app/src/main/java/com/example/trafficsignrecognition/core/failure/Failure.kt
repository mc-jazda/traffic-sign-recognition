package com.example.trafficsignrecognition.core.failure

abstract class Failure : Exception() {
    abstract val errorMessage: String

    open var description: String? = null

    override fun toString(): String {
        return "Failure(errorMessage='$errorMessage', description=$description)"
    }
}

class UseCaseResultStateFail : Failure() {
    override val errorMessage: String
        get() = "Coś poszło nie tak..."
}

class SignDetectorDetectingFailure : Failure() {
    override val errorMessage: String
        get() = "Coś poszło nie tak podczas rozpoznawania znaku..."
}

class SignDetectorSetupFailure : Failure() {
    override val errorMessage: String
        get() = "Sign detector setup failed!"
}

class SignDetectorClearingFailure : Failure() {
    override val errorMessage: String
        get() = "Clearing after sign detector failed!"
}

class TextToSpeechInitFailure : Failure() {
    override val errorMessage: String
        get() = "Text to speech initialization failed!"
}

class TextToSpeechSpeakFailure : Failure() {
    override val errorMessage: String
        get() = "Text to speech speak failed!"
}

class FetchLocationFailure : Failure() {
    override val errorMessage: String
        get() = "Fetching location failed!"
}

class LocationNullFailure : Failure() {
    override val errorMessage: String
        get() = "Location is null"
}