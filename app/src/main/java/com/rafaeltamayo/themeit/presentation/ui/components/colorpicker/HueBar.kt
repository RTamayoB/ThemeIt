package com.rafaeltamayo.themeit.presentation.ui.components.colorpicker

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.drag
import androidx.compose.foundation.gestures.forEachGesture
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.consumePositionChange
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import com.rafaeltamayo.themeit.presentation.ui.components.drawVerticalSelector

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
            .padding(4.dp)
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
        drawVerticalSelector(huePoint)
    }
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