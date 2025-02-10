package com.example.trafficsignrecognition.features.recognizer.ui.widgets

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.trafficsignrecognition.features.recognizer.domain.viewmodels.CurrentSpeedViewModel

@Composable
fun CurrentSpeed(
    modifier: Modifier = Modifier,
    viewModel: CurrentSpeedViewModel = viewModel(),
) {
    val speedState by viewModel.speedState.collectAsState()
    val errorState by viewModel.errorMessage.collectAsState()

    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(Unit) {
        viewModel.fetchSpeed()
    }

    LaunchedEffect(errorState) {
        errorState?.let {
            snackbarHostState.showSnackbar(it)
        }
    }
    Box(
        modifier = modifier
            .shadow(
                elevation = 8.dp,
                shape = RoundedCornerShape(20.dp),
                clip = true
            )
            .clip(RoundedCornerShape(20.dp))
            .background(MaterialTheme.colorScheme.tertiaryContainer)
    ) {

        Text(
            "Aktualna prędkość: $speedState km/h",
            style = MaterialTheme.typography.headlineSmall,
            modifier = modifier
                .padding(horizontal = 15.dp, vertical = 10.dp)
        )
    }
}