package com.example.trafficsignrecognition.features.recognizer.ui.widgets

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.trafficsignrecognition.R

val textToImg = mapOf(
    "Przystanek autobusowy" to R.drawable.pl_road_sign_d15,
    "Zakaz ruchu w obu kierunkach" to R.drawable.pl_road_sign_b1,
    "Zakaz wjazdu" to R.drawable.pl_road_sign_b2,
    "Stop" to R.drawable.pl_road_sign_b20,
    "Zakaz skręcania w lewo" to R.drawable.pl_road_sign_b21,
    "Zakaz skręcania w prawo" to R.drawable.pl_road_sign_b22,
    "Zakaz zawracania" to R.drawable.pl_road_sign_b23,
    "Ograniczenie prędkości" to R.drawable.pl_road_sign_b33_50,
    "Koniec ograniczenia prędkości" to R.drawable.pl_road_sign_b34_50,
    "Zakaz postoju" to R.drawable.pl_road_sign_b35,
    "Zakaz zatrzymywania się" to R.drawable.pl_road_sign_b36,
    "Nakaz jazdy w prawo za znakiem" to R.drawable.pl_road_sign_c2,
    "Nakaz jazdy prosto" to R.drawable.pl_road_sign_c5,
    "Nakaz jazdy z prawej strony znaku" to R.drawable.pl_road_sign_c9,
    "Ruch okrężny" to R.drawable.pl_road_sign_c12,
    "Droga dla pieszych i rowerów" to R.drawable.pl_road_sign_c_13_16,
    "Droga z pierwszeństwem" to R.drawable.pl_road_sign_d1,
    "Koniec drogi z pierwszeństwem" to R.drawable.pl_road_sign_d2,
    "Przejście dla pieszych" to R.drawable.pl_road_sign_d6,
    "Parking" to R.drawable.pl_road_sign_d18,
    "Zakaz wjazdu samochodów ciężarowych" to R.drawable.pl_road_sign_b5,
    "Ustąp pierwszeństwa" to R.drawable.pl_road_sign_a7,
    "Droga jednokierunkowa" to R.drawable.pl_road_sign_d3,
    "None" to R.drawable.eye
)

@Composable
fun SignWidget(
    signName: String?,
    modifier: Modifier = Modifier,
) {
    val cornerRadius = 16.dp
    val squareSize = 150.dp
    val imagePadding = 12.dp

    val imageResource = textToImg[signName] ?: textToImg["None"]!!

    Box(
        modifier = modifier
            .size(squareSize)
            .clip(RoundedCornerShape(cornerRadius))
            .background(MaterialTheme.colorScheme.primaryContainer) // Background color
            //.shadow(elevation, shape = RoundedCornerShape(cornerRadius)) // Add elevation
    ) {
        AnimatedContent(
            targetState = imageResource,
            label = "sign animation",
        ) {
            currentImageResource ->
            Image(
                painter = painterResource(id = currentImageResource),
                contentDescription = "Image inside square with padding",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(imagePadding)
            )
        }
    }
}