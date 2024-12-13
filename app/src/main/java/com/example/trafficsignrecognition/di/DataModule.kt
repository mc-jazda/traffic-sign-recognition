package com.example.trafficsignrecognition.di

import android.app.Application
import android.content.Context
import com.example.trafficsignrecognition.core.failure.ErrorHandler
import com.example.trafficsignrecognition.core.failure.ErrorHandlerImpl
import com.example.trafficsignrecognition.features.recognizer.data.datasources.SignsDetectorDatasource
import com.example.trafficsignrecognition.features.recognizer.data.datasources.SignsDetectorDatasourceImpl
import com.example.trafficsignrecognition.features.recognizer.data.repositories.SignsDetectorRepositoryImpl
import com.example.trafficsignrecognition.features.recognizer.domain.repositories.SignsDetectorRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

//@Module
//@InstallIn(SingletonComponent::class)
//object AppModule {
//
//    @Provides
//    @Singleton
//    @ApplicationContext
//    fun provideApplicationContext(application: Application): Context {
//        return application.applicationContext
//    }
//}

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
