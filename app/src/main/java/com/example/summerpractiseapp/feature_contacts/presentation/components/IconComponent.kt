package com.example.summerpractiseapp.feature_contacts.presentation.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.summerpractiseapp.R

@Composable
fun IconComponent(
    modifier: Modifier = Modifier,
    icon: Int,
    contentDescription: String?,
    circleColor: Color,
    iconSize: Dp = 18.dp,
    iconTint: Color = Color.White
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Canvas(
            modifier = Modifier.size(40.dp)
        ) {
            drawCircle(
                color = circleColor,
                style = Stroke(width = 1.dp.toPx())
            )
        }
        Icon(painter = painterResource(id = icon), contentDescription = contentDescription, modifier = Modifier.size(iconSize), tint = iconTint)
    }
}