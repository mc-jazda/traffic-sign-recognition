package com.example.trafficsignrecognition.core.usecase

sealed class BaseUseCase<Type, Params>

abstract class UseCase<Type, Params> : BaseUseCase<Type, Params>() {
    abstract operator fun invoke(params: Params): UseCaseResult<Type>
}

abstract class AsyncUseCase<Type, Params> : BaseUseCase<Type, Params>() {
    abstract suspend operator fun invoke(params: Params): UseCaseResult<Type>
}

class NoParams {}