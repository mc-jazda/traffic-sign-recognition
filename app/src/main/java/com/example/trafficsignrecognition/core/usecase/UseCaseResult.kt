package com.example.trafficsignrecognition.core.usecase

import com.example.trafficsignrecognition.core.failure.Failure

sealed class UseCaseResult<T>(
    val state: UseCaseResultState,
    val resultFailure: Failure? = null,
    val resultData: T? = null
) {

    abstract val data: T
    abstract val failure: Failure

    val isSuccess: Boolean
        get() = state == UseCaseResultState.SUCCESS

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is UseCaseResult<*>) return false
        return resultFailure == other.resultFailure && resultData == other.resultData
    }

    override fun hashCode(): Int {
        var result = resultFailure?.hashCode() ?: 0
        result = 31 * result + (resultData?.hashCode() ?: 0)
        return result
    }

    companion object {
        fun <T> success(data: T? = null): UseCaseResult<T> {
            return UseCaseResultData(data)
        }

        fun <T> error(error: Failure): UseCaseResult<T> {
            return UseCaseResultFailure(error)
        }
    }
}

class UseCaseResultFailure<T>(
    resultFailure: Failure
) : UseCaseResult<T>(
    state = UseCaseResultState.FAILURE,
    resultFailure = resultFailure,
    resultData = null
) {
    override val data: T
        get() = throw UseCaseResultStateFail()

    override val failure: Failure
        get() = resultFailure ?: throw UseCaseResultStateFail()
}

class UseCaseResultData<T>(
    resultData: T?
) : UseCaseResult<T>(
    state = UseCaseResultState.SUCCESS,
    resultFailure = null,
    resultData = resultData
) {
    override val data: T
        get() = resultData ?: throw UseCaseResultStateFail()

    override val failure: Failure
        get() = throw UseCaseResultStateFail()
}

enum class UseCaseResultState {
    SUCCESS, FAILURE
}

class UseCaseResultStateFail : RuntimeException()
