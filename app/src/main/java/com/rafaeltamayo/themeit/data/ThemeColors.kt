package com.rafaeltamayo.themeit.data

import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.listSaver
import androidx.compose.ui.graphics.Color
import com.rafaeltamayo.themeit.presentation.ui.theme.*

data class ThemeColors(
    var primary: Color = Purple500,
    var secondary: Color = Teal200,
    var primaryVariant: Color = Purple700,
    var secondaryVariant: Color = Teal700,
    var background: Color = White,
    var surface: Color = White,
    var error: Color = Red,
    var onPrimary: Color = White,
    var onSecondary: Color = Black,
    var onBackground: Color = Black,
    var onSurface: Color = Black,
    var onError: Color = White
) {

    companion object {
        val Saver: Saver<ThemeColors, *> = listSaver(
            save = {
                listOf(
                    it.primary,
                    it.secondary,
                    it.primaryVariant,
                    it.secondaryVariant,
                    it.background,
                    it.surface,
                    it.error,
                    it.onPrimary,
                    it.onSecondary,
                    it.onBackground,
                    it.onSurface,
                    it.onError,
                )
            },
            restore = {
                ThemeColors(
                    it[0],
                    it[1],
                    it[2],
                    it[3],
                    it[4],
                    it[5],
                    it[6],
                    it[7],
                    it[8],
                    it[9],
                    it[10],
                    it[11]
                )
            }
        )
    }
}