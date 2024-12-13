package com.example.trafficsignrecognition.features.recognizer.data.models

import com.example.trafficsignrecognition.features.recognizer.domain.entities.BoundingBox

class BoundingBoxModel(
    x1: Float,
    y1: Float,
    x2: Float,
    y2: Float,
    cx: Float,
    cy: Float,
    w: Float,
    h: Float,
    cnf: Float,
    cls: Int,
    clsName: String
) : BoundingBox(x1, y1, x2, y2, cx, cy, w, h, cnf, cls, clsName)