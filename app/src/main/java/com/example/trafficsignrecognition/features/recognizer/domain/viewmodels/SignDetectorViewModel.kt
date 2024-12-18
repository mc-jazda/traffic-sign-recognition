package com.example.trafficsignrecognition.features.recognizer.domain.viewmodels

import android.graphics.Bitmap
import android.graphics.Matrix
import android.util.Log
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import androidx.camera.core.Preview
import androidx.camera.core.resolutionselector.ResolutionSelector
import androidx.camera.core.resolutionselector.ResolutionStrategy
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.trafficsignrecognition.core.usecase.NoParams
import com.example.trafficsignrecognition.core.usecase.UseCaseResultData
import com.example.trafficsignrecognition.core.usecase.UseCaseResultFailure
import com.example.trafficsignrecognition.features.recognizer.domain.usecases.ClearSignDetectorUseCase
import com.example.trafficsignrecognition.features.recognizer.domain.usecases.DetectSignsParams
import com.example.trafficsignrecognition.features.recognizer.domain.usecases.DetectSignsUseCase
import com.example.trafficsignrecognition.features.recognizer.domain.usecases.SetupSignDetectorUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.concurrent.Executors
import javax.inject.Inject

@HiltViewModel
class SignDetectorViewModel @Inject constructor(
    setupSignDetectorUseCase: SetupSignDetectorUseCase,
    private val detectSignsUseCase: DetectSignsUseCase,
    private val clearSignDetectorUseCase: ClearSignDetectorUseCase,
) : ViewModel() {
    private val _detectedSignText = MutableStateFlow<String?>(null)
    val detectedSignText: StateFlow<String?> = _detectedSignText

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> get() = _errorMessage

    private var frameSkipCounter = 0

    init {
        val result = setupSignDetectorUseCase(NoParams())
        if (result is UseCaseResultFailure) {
            Log.v("SETUP: ", result.failure.errorMessage)
        }

    }

    private fun detectSigns(params: DetectSignsParams) {
        viewModelScope.launch(Dispatchers.Default) {
            detectSignsUseCase(params)
                .collect { result ->
                    when (result) {
                        is UseCaseResultData -> {
                            _detectedSignText.value = when {
                                !result.data.isBoxListEmpty -> {
                                    result.data.boundingBoxes.firstOrNull()?.clsName ?: "Unknown"
                                }
                                else -> "None"
                            }
                        }

                        is UseCaseResultFailure -> {
                            _errorMessage.value = result.failure.errorMessage
                        }
                    }
                }
        }
    }

    fun startCamera(previewView: PreviewView) {
        val cameraProviderFuture = ProcessCameraProvider.getInstance(previewView.context)
        cameraProviderFuture.addListener({
            val cameraProvider = cameraProviderFuture.get()
            val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA
            val resolutionSelector = ResolutionSelector.Builder()
                .setResolutionStrategy(
                    ResolutionStrategy(
                        android.util.Size(600, 900),
                        ResolutionStrategy.FALLBACK_RULE_CLOSEST_LOWER,
                    )
                )
                .build()


            val preview = Preview.Builder()
                .setTargetRotation(previewView.display.rotation)
                .build().also {
                    it.surfaceProvider = previewView.surfaceProvider
                }

            val imageAnalyzer = ImageAnalysis.Builder()
                //.setResolutionSelector(resolutionSelector)
                .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
                .setOutputImageFormat(ImageAnalysis.OUTPUT_IMAGE_FORMAT_RGBA_8888)
                .build().also {
                    it.setAnalyzer(Executors.newCachedThreadPool()) { imageProxy ->
                        val bitmap: Bitmap
                        if(frameSkipCounter % 30 == 0) {
                            imageProxy.use { bitmap = proxyToBitmap(imageProxy) }
                            detectSigns(DetectSignsParams(bitmap))
                        }
                        frameSkipCounter++
                        imageProxy.close()
                    }
                }

            cameraProvider.unbindAll()
            cameraProvider.bindToLifecycle(
                previewView.context as LifecycleOwner,
                cameraSelector,
                preview,
                imageAnalyzer
            )
        }, ContextCompat.getMainExecutor(previewView.context))
    }

    private fun proxyToBitmap(imageProxy: ImageProxy): Bitmap {
        val bitmapBuffer =
            Bitmap.createBitmap(imageProxy.width, imageProxy.height, Bitmap.Config.ARGB_8888)
        imageProxy.use {
            bitmapBuffer.copyPixelsFromBuffer(imageProxy.planes[0].buffer)
        }

        return if (imageProxy.imageInfo.rotationDegrees != 0) {
            val matrix = Matrix().apply {
                postRotate(imageProxy.imageInfo.rotationDegrees.toFloat())
            }
            Bitmap.createBitmap(
                bitmapBuffer,
                0,
                0,
                bitmapBuffer.width,
                bitmapBuffer.height,
                matrix,
                true
            )
        } else {
            bitmapBuffer
        }
    }

    override fun onCleared() {
        clearSignDetectorUseCase(NoParams())
        super.onCleared()
    }
}