package com.example.trafficsignrecognition.core.failure

import javax.inject.Inject

interface ErrorHandler {
    fun handleError(error: Any, defaultFailure: Failure): Failure
}

class ErrorHandlerImpl @Inject constructor() : ErrorHandler {
    override fun handleError(error: Any, defaultFailure: Failure): Failure {
        return if (error is Failure)
            error
        else
            defaultFailure
    }
}