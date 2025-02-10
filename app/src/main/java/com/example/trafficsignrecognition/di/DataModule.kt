package com.example.trafficsignrecognition.di

import com.example.trafficsignrecognition.core.failure.ErrorHandler
import com.example.trafficsignrecognition.core.failure.ErrorHandlerImpl
import com.example.trafficsignrecognition.features.recognizer.data.datasources.CurrentSpeedDataSource
import com.example.trafficsignrecognition.features.recognizer.data.datasources.CurrentSpeedDataSourceImpl
import com.example.trafficsignrecognition.features.recognizer.data.datasources.SignsDetectorDatasource
import com.example.trafficsignrecognition.features.recognizer.data.datasources.SignsDetectorDatasourceImpl
import com.example.trafficsignrecognition.features.recognizer.data.datasources.TextToSpeechDatasource
import com.example.trafficsignrecognition.features.recognizer.data.datasources.TextToSpeechDatasourceImpl
import com.example.trafficsignrecognition.features.recognizer.data.repositories.CurrentSpeedRepositoryImpl
import com.example.trafficsignrecognition.features.recognizer.data.repositories.SignsDetectorRepositoryImpl
import com.example.trafficsignrecognition.features.recognizer.data.repositories.TextToSpeechRepositoryImpl
import com.example.trafficsignrecognition.features.recognizer.domain.repositories.CurrentSpeedRepository
import com.example.trafficsignrecognition.features.recognizer.domain.repositories.SignsDetectorRepository
import com.example.trafficsignrecognition.features.recognizer.domain.repositories.TextToSpeechRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class SignsDetectorDataSourceModule {
    @Singleton
    @Binds
    abstract fun bindSignsDetectorDataSource(
        datasource: SignsDetectorDatasourceImpl
    ): SignsDetectorDatasource
}

@Module
@InstallIn(SingletonComponent::class)
abstract class SignsDetectorRepositoryModule {
    @Singleton
    @Binds
    abstract fun bindSignsDetectorRepositoryModule(
        repository: SignsDetectorRepositoryImpl
    ): SignsDetectorRepository
}

@Module
@InstallIn(SingletonComponent::class)
abstract class ErrorHandlerModule {
    @Singleton
    @Binds
    abstract fun provideErrorHandler(
        errorHandler: ErrorHandlerImpl
    ): ErrorHandler
}

// text-to-speech
@Module
@InstallIn(SingletonComponent::class)
abstract class TextToSpeechDataSourceModule {
    @Singleton
    @Binds
    abstract fun bindTextToSpeechDataSource(
        datasource: TextToSpeechDatasourceImpl
    ): TextToSpeechDatasource
}

@Module
@InstallIn(SingletonComponent::class)
abstract class TextToSpeechRepositoryModule {
    @Singleton
    @Binds
    abstract fun bindTextToSpeechRepositoryModule(
        repository: TextToSpeechRepositoryImpl,
    ): TextToSpeechRepository
}

// current speed
@Module
@InstallIn(SingletonComponent::class)
abstract class CurrentSpeedDataSourceModule {
    @Singleton
    @Binds
    abstract fun bindCurrentSpeedDataSource(
        datasource: CurrentSpeedDataSourceImpl
    ): CurrentSpeedDataSource
}

@Module
@InstallIn(SingletonComponent::class)
abstract class CurrentSpeedRepositoryModule {
    @Singleton
    @Binds
    abstract fun bindCurrentSpeedRepositoryModule(
        repository: CurrentSpeedRepositoryImpl
    ): CurrentSpeedRepository
}
