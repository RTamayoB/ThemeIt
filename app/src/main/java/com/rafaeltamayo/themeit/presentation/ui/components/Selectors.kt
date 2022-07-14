package com.rafaeltamayo.themeit.presentation.ui.components

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import com.rafaeltamayo.themeit.presentation.ui.components.colorpicker.HSVColor
import com.rafaeltamayo.themeit.presentation.ui.components.colorpicker.inset

internal fun DrawScope.drawVerticalSelector(amount: Float) {
    val halfIndicatorThickness = 4.dp.toPx()
    val strokeThickness = 1.dp.toPx()

    val offset =
        Offset(
            y = amount - halfIndicatorThickness,
            x = -strokeThickness
        )
    val selectionSize = Size(this.size.width + strokeThickness * 2, halfIndicatorThickness * 2f)
    drawSelectorIndicator(
        offset = offset,
        selectionSize = selectionSize,
        strokeThicknessPx = strokeThickness
    )
}

internal fun DrawScope.drawSelectorIndicator(
    offset: Offset,
    selectionSize: Size,
    strokeThicknessPx: Float
) {
    val selectionStyle = Stroke(strokeThicknessPx)
    drawRect(
        Color.Gray,
        topLeft = offset,
        size = selectionSize,
        style = selectionStyle
    )
    drawRect(
        Color.White,
        topLeft = offset + Offset(strokeThicknessPx, strokeThicknessPx),
        size = selectionSize.inset(2 * strokeThicknessPx),
        style = selectionStyle
    )
}

internal fun DrawScope.drawCircleSelector(currentColor: HSVColor, saturationPoint: Offset) {
    val radius = 6.dp
    val circleStyle = Stroke(2.dp.toPx())
    drawCircle(
        color = Color.White,
        radius = radius.toPx(),
        center = saturationPoint,
        style = circleStyle
    )
    drawCircle(
        color = Color.Gray,
        radius = (radius - 2.dp).toPx(),
        center = saturationPoint,
        style = Stroke(1.dp.toPx())
    )
}