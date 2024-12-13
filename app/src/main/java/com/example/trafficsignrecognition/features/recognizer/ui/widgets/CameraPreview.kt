package com.example.trafficsignrecognition.features.recognizer.ui.widgets

import androidx.camera.view.PreviewView
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView

@Composable
fun CameraPreview(
    startCamera: (PreviewView) -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val previewView = remember { PreviewView(context) }

    AndroidView(
        factory = { previewView },
        modifier = modifier
            .clip(RoundedCornerShape(16.dp))
    ) {
        startCamera(previewView)
    }
}