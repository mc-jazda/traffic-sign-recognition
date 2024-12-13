package com.example.trafficsignrecognition.features.recognizer.data.models

import com.example.trafficsignrecognition.features.recognizer.domain.entities.BoundingBox
import com.example.trafficsignrecognition.features.recognizer.domain.entities.SignDetectorResult

class SignDetectorResultModel(
    boundingBoxes: List<BoundingBox>,
    inferenceTime: Long,
    isBoxListEmpty: Boolean = false
) : SignDetectorResult(
    boundingBoxes,
    inferenceTime,
    isBoxListEmpty
)