package com.example.trafficsignrecognition.features.recognizer.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.trafficsignrecognition.features.recognizer.domain.viewmodels.SignDetectorViewModel
import com.example.trafficsignrecognition.features.recognizer.ui.widgets.CameraPreview

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    signDetectorViewModel: SignDetectorViewModel = viewModel(),
) {
    val signDetectionResult by signDetectorViewModel.signDetectionResult.collectAsState()
    val detectedSignText = when {
        signDetectionResult?.isBoxListEmpty == false -> {
            signDetectionResult?.boundingBoxes?.firstOrNull()?.clsName ?: "Unknown"
        }
        else -> "None"
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp, vertical = 20.dp),
    ) {
        CameraPreview(
            startCamera = { previewView -> signDetectorViewModel.startCamera(previewView) },
            modifier = Modifier
                .fillMaxWidth()
                .height(500.dp)
        )
        Text(text = "Detected Sign: $detectedSignText")
    }
}