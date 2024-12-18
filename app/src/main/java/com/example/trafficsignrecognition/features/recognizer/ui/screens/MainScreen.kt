package com.example.trafficsignrecognition.features.recognizer.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.trafficsignrecognition.features.recognizer.domain.viewmodels.SignDetectorViewModel
import com.example.trafficsignrecognition.features.recognizer.domain.viewmodels.TextToSpeechViewModel
import com.example.trafficsignrecognition.features.recognizer.ui.widgets.CameraPreview

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    signDetectorViewModel: SignDetectorViewModel = viewModel(),
    textToSpeechViewModel: TextToSpeechViewModel = viewModel(),
) {
    val detectedSignText by signDetectorViewModel.detectedSignText.collectAsState()
    val detectorErrorMessage by signDetectorViewModel.errorMessage.collectAsState()
    val textToSpeechErrorMessage by textToSpeechViewModel.errorMessage.collectAsState()

    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(detectorErrorMessage, textToSpeechErrorMessage) {
        detectorErrorMessage?.let {
            snackbarHostState.showSnackbar(it)
        }
        textToSpeechErrorMessage?.let {
            snackbarHostState.showSnackbar(it)
        }
    }

    LaunchedEffect(detectedSignText) {
        if (detectedSignText != null && detectedSignText!!.isNotEmpty()) {
            textToSpeechViewModel.speak(detectedSignText!!)
        }
    }

    Scaffold(
        snackbarHost = {
            SnackbarHost(snackbarHostState)
        }
    ) { paddingValues ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
                .fillMaxSize()
                .padding(paddingValues)
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
}