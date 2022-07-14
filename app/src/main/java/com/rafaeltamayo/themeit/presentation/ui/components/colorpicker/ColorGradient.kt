package com.rafaeltamayo.themeit.presentation.ui.components.colorpicker

import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.drag
import androidx.compose.foundation.gestures.forEachGesture
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.consumePositionChange
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import com.rafaeltamayo.themeit.presentation.ui.components.drawCircleSelector


@Composable
fun ColorGradient(
    modifier: Modifier = Modifier,
    currentColor: HSVColor,
    onColorGradientChanged: (saturation: Float, value: Float) -> Unit
) {
    val blackGradient = remember {
        Brush.verticalGradient(listOf(Color(0xFFFFFFFF), Color(0xFF000000)))
    }

    val colorGradient = remember(currentColor.hue) {
        val colorGradient = currentColor.getGradientColor()
        Brush.horizontalGradient(listOf(Color(0xFFFFFFFF), colorGradient))
    }

    Canvas(modifier = modifier
        .padding(4.dp)
        .fillMaxSize()
        .pointerInput(Unit) {
            forEachGesture {
                awaitPointerEventScope {
                    val down = awaitFirstDown()
                    val (s, v) = getSaturationPoint(down.position, size)
                    onColorGradientChanged(s, v)
                    drag(down.id) { pointerInputChange ->
                        pointerInputChange.consumePositionChange()
                        val (newSaturation, newValue) = getSaturationPoint(pointerInputChange.position, size)
                        onColorGradientChanged(newSaturation, newValue)
                    }
                }
            }
        }) {
        drawRect(blackGradient)
        drawRect(colorGradient, blendMode = BlendMode.Modulate)
        drawRect(Color.Gray, style = Stroke(0.5.dp.toPx()))

        drawCircleSelector(currentColor, getSaturationValuePoint(currentColor, size))
    }
}

private fun getSaturationPoint(
    offset: Offset,
    size: IntSize
): Pair<Float, Float> {
    val (saturation, value) = getSaturationValueFromPosition(
        offset,
        size.toSize()
    )
    return saturation to value
}

private fun getSaturationValuePoint(color: HSVColor, size: Size): Offset {
    val height: Float = size.height
    val width: Float = size.width

    return Offset((color.saturation * width), (1f - color.value) * height)
}

private fun getSaturationValueFromPosition(offset: Offset, size: Size): Pair<Float, Float> {
    val width = size.width
    val height = size.height

    val newX = offset.x.coerceIn(0f, width)

    val newY = offset.y.coerceIn(0f, size.height)
    val saturation = 1f / width * newX
    val value = 1f - 1f / height * newY

    return saturation.coerceIn(0f, 1f) to value.coerceIn(0f, 1f)
}