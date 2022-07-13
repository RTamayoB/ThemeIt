package com.rafaeltamayo.themeit.presentation.ui.components.colorpicker

import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.drag
import androidx.compose.foundation.gestures.forEachGesture
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.consumePositionChange
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp

@Composable
fun HueBar(
    modifier: Modifier = Modifier,
    hueColor: HSVColor,
    onHueChanged: (Float) -> Unit
) {
    val rainbowGradient = remember {
        Brush.verticalGradient(getRainbow())
    }
    Canvas(
        modifier = modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                forEachGesture {
                    awaitPointerEventScope {
                        val down = awaitFirstDown()
                        onHueChanged(getHueFromPoint(down.position.y, size.height.toFloat()))
                        drag(down.id) { pointerInputChange ->
                            pointerInputChange.consumePositionChange()
                            onHueChanged(getHueFromPoint(pointerInputChange.position.y, size.height.toFloat()))
                        }
                    }
                }
            }
    ) {
        drawRect(rainbowGradient)
        drawRect(Color.Gray, style = Stroke(0.5.dp.toPx()))

        val huePoint = getPointFromHue(color = hueColor, height = this.size.height)
        Log.d("HuePoint", huePoint.toString())
        drawVerticalSelector(huePoint)
    }
}

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


internal fun Size.inset(amount: Float): Size {
    return Size(width - amount, height - amount)
}

private fun getRainbow(): List<Color> {
    return listOf(
        Color(0xFFFF0040),
        Color(0xFFFF00FF),
        Color(0xFF8000FF),
        Color(0xFF0000FF),
        Color(0xFF0080FF),
        Color(0xFF00FFFF),
        Color(0xFF00FF80),
        Color(0xFF00FF00),
        Color(0xFF80FF00),
        Color(0xFFFFFF00),
        Color(0xFFFF8000),
        Color(0xFFFF0000),
    )
}

private fun getPointFromHue(color: HSVColor, height: Float): Float {
    return height - (color.hue * height / 360f)
}

private fun getHueFromPoint(y: Float, height: Float): Float {
    val newY = y.coerceIn(0f, height)
    return 360f - newY * 360f / height
}