package com.example.trafficsignrecognition.features.recognizer.domain.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.trafficsignrecognition.core.usecase.NoParams
import com.example.trafficsignrecognition.core.usecase.UseCaseResultData
import com.example.trafficsignrecognition.core.usecase.UseCaseResultFailure
import com.example.trafficsignrecognition.features.recognizer.domain.usecases.GetCurrentSpeedUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CurrentSpeedViewModel @Inject constructor(
    private val getCurrentSpeedUseCase: GetCurrentSpeedUseCase
) : ViewModel() {
    private val _speedState = MutableStateFlow(0.0f)
    val speedState: StateFlow<Float> get() = _speedState

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> get() = _errorMessage

    fun fetchSpeed() {
        viewModelScope.launch {
            while (true){
                getCurrentSpeedUseCase(NoParams()).collect { result ->
                    when (result) {
                        is UseCaseResultData -> {
                            _speedState.value = result.data
                        }

                        is UseCaseResultFailure -> {
                            _errorMessage.value = result.failure.errorMessage
                        }
                    }
                }
            }
        }
    }
}