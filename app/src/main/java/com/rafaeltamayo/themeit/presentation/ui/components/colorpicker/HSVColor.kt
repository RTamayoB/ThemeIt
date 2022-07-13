package com.rafaeltamayo.themeit.presentation.ui.components.colorpicker

import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.listSaver
import android.graphics.Color as ColorGraphics
import androidx.compose.ui.graphics.Color as ColorCompose

data class HSVColor(
    val hue: Float,
    val saturation: Float,
    val value: Float,
    val alpha: Float
) {

    fun toColor(): ColorCompose {
        val hsvArray = floatArrayOf(hue, saturation, value)
        val colorInt = ColorGraphics.HSVToColor(hsvArray)
        return ColorCompose(colorInt)
    }

    companion object {


        fun from(color: ColorCompose): HSVColor {
            return HSVColor(
                color.red,
                color.green,
                color.blue,
                color.alpha,
            )
        }

        val Saver: Saver<HSVColor, *> = listSaver(
            save = {
                listOf(
                    it.hue,
                    it.saturation,
                    it.value,
                    it.alpha
                )
            },
            restore = {
                HSVColor(
                    it[0],
                    it[1],
                    it[2],
                    it[3],
                )
            }
        )
    }
}
