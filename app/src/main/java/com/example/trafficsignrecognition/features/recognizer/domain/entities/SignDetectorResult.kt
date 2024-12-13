package com.example.trafficsignrecognition.features.recognizer.domain.entities

abstract class SignDetectorResult(
    val boundingBoxes: List<BoundingBox>,
    val inferenceTime: Long,
    val isBoxListEmpty: Boolean = false
)