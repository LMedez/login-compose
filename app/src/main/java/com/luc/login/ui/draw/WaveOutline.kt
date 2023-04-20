package com.luc.login.ui.draw

import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection

class WaveOutline(
    private val startEndYHeights: Pair<Float, Float>,
    private val controlPoint1: Pair<Float, Float>,
    private val controlPoint2: Pair<Float, Float>
) : Shape {
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        return Outline.Generic(Path().apply {
            reset()

            // go from (0,0) to (width, 0)
            lineTo(size.width, 0f)

            // go from (width, 0) to (width, height)
            lineTo(size.width, size.height * startEndYHeights.second)

            cubicTo(
                size.width * controlPoint1.first,
                size.height * controlPoint1.second,
                size.width * controlPoint2.first,
                size.height * controlPoint2.second,
                0f,
                size.height * startEndYHeights.first
            )

            // go from (0, height) to (0, 0)
            lineTo(0f, 0f)
            close()
        })
    }
}
