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
        get() = "Something went wrong..."
}

class SignDetectorDetectingFailure : Failure() {
    override val errorMessage: String
        get() = "Something went wrong during signs detection..."
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