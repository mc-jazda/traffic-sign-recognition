package com.example.trafficsignrecognition.features.recognizer.domain.viewmodels

import androidx.camera.core.CameraSelector
import androidx.camera.core.CameraState
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel

class CameraViewModel : ViewModel() {
    private val _cameraState = MutableLiveData<CameraState>()
    val cameraState: LiveData<CameraState> = _cameraState

    fun startCamera(previewView: PreviewView) {
        val cameraProviderFuture = ProcessCameraProvider.getInstance(previewView.context)
        cameraProviderFuture.addListener({
            val cameraProvider = cameraProviderFuture.get()
            val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA

            val preview = Preview.Builder()
                .setTargetRotation(previewView.display.rotation)
                .build().also {
                    it.surfaceProvider = previewView.surfaceProvider
                }

            cameraProvider.unbindAll()
            cameraProvider.bindToLifecycle(
                previewView.context as LifecycleOwner,
                cameraSelector,
                preview
            )
        }, ContextCompat.getMainExecutor(previewView.context))
    }
}