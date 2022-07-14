package com.rafaeltamayo.themeit.presentation.views

import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rafaeltamayo.themeit.data.ThemeColors
import com.rafaeltamayo.themeit.presentation.ui.components.ColorCard
import com.rafaeltamayo.themeit.presentation.ui.components.ColorPickerDialog
import com.rafaeltamayo.themeit.presentation.ui.components.LargeColorCard
import com.rafaeltamayo.themeit.presentation.ui.components.MediumColorCard
import com.rafaeltamayo.themeit.presentation.ui.theme.*

@Composable
/*
TODO:
 From now everytime the dialog closes, checks at which
 card opened it, and changes the correct value:
 when(card) {
    "primColor" -> Theme.primaryColor = colorFromDialog
 Finally, all cards are going to access their respective value:
    backgroundColor = Theme.primaryColor
 */
fun CreateTheme() {
    val themeColors = remember { mutableStateOf(ThemeColors()) }
    val showDialog = remember { mutableStateOf(false) }
    val currentColorCard = remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
    ) {
        Row(modifier = Modifier.padding(2.dp)) {
            LargeColorCard(
                modifier = Modifier.weight(1.0F),
                onClick = {
                    showDialog.value = true
                    currentColorCard.value = "primary"
                          },
                backgroundColor = themeColors.value.primary,
                title = "Primary Color",
                colorCode = "0xFF6200EE"
            )
            LargeColorCard(
                modifier = Modifier.weight(1.0F),
                onClick = {
                    showDialog.value = true
                    currentColorCard.value = "secondary"
                          },
                backgroundColor = themeColors.value.secondary,
                title = "Secondary Color",
                colorCode = "0xFF03DAC5"
            )
        }
        Row(modifier = Modifier.padding(2.dp)) {
            MediumColorCard(
                modifier = Modifier.weight(1.0F),
                onClick = {
                    showDialog.value = true
                    currentColorCard.value = "primaryVariant"
                },
                backgroundColor = themeColors.value.primaryVariant,
                title = "Primary Variant",
                colorCode = "0xFF3700B3"
            )
            MediumColorCard(
                modifier = Modifier.weight(1.0F),
                onClick = {
                    showDialog.value = true
                    currentColorCard.value = "secondaryVariant"
                },
                backgroundColor = themeColors.value.secondaryVariant,
                title = "Secondary Variant",
                colorCode = "0xFF027C6F"
            )
        }
        Divider()
        Row(modifier = Modifier.padding(2.dp)) {
            MediumColorCard(
                modifier = Modifier.weight(1.0F),
                onClick = {
                    showDialog.value = true
                    currentColorCard.value = "background"
                },
                backgroundColor = themeColors.value.background,
                title = "Background",
                colorCode = "0xFFFFFFFF"
            )
            MediumColorCard(
                modifier = Modifier.weight(1.0F),
                onClick = {
                    showDialog.value = true
                    currentColorCard.value = "surface"
                },
                backgroundColor = themeColors.value.surface,
                title = "Surface",
                colorCode = "0xFFFFFFFF"
            )
            MediumColorCard(
                modifier = Modifier.weight(1.0F),
                onClick = {
                    showDialog.value = true
                    currentColorCard.value = "error"
                },
                backgroundColor = themeColors.value.error,
                title = "Error",
                colorCode = "0xFFB00020"
            )
        }
        Divider()
        Row(modifier = Modifier.padding(2.dp)) {
            MediumColorCard(
                modifier = Modifier.weight(1.0F),
                onClick = {
                    showDialog.value = true
                    currentColorCard.value = "onPrimary"
                },
                backgroundColor = themeColors.value.onPrimary,
                title = "On Primary",
                colorCode = "0xFFFFFFFF"
            )
            MediumColorCard(
                modifier = Modifier.weight(1.0F),
                onClick = {
                    showDialog.value = true
                    currentColorCard.value = "onSecondary"
                },
                backgroundColor = themeColors.value.onSecondary,
                title = "On Secondary",
                colorCode = "0xFFFFFFFF"
            )
        }
        Row(modifier = Modifier.padding(2.dp)) {
            MediumColorCard(
                modifier = Modifier.weight(1.0F),
                onClick = {
                    showDialog.value = true
                    currentColorCard.value = "onBackground"
                },
                backgroundColor = themeColors.value.onBackground,
                title = "On Background",
                colorCode = "0xFFFFFFFF"
            )
            MediumColorCard(
                modifier = Modifier.weight(1.0F),
                onClick = {
                    showDialog.value = true
                    currentColorCard.value = "onSurface"
                },
                backgroundColor = themeColors.value.onSurface,
                title = "On Surface",
                colorCode = "0xFFFFFFFF"
            )
            MediumColorCard(
                modifier = Modifier.weight(1.0F),
                onClick = {
                    showDialog.value = true
                    currentColorCard.value = "onError"
                },
                backgroundColor = themeColors.value.onError,
                title = "On Error",
                colorCode = "0xFFB00020"
            )
        }
    }
    if(showDialog.value) {
        var showedColor = Color.Black
        ColorPickerDialog(
            onDismissRequest = {
                showDialog.value = false
                setSelectedCard(currentColorCard.value, themeColors.value, showedColor)
                               },
            onColorChanged = {
                showedColor= it.toColor()
            }
        )
    }
}

private fun setSelectedCard(card: String, themeColors: ThemeColors, newColor: Color) {
    when(card) {
        "primary" -> themeColors.primary = newColor
        "secondary" -> themeColors.secondary = newColor
        "primaryVariant" -> themeColors.primaryVariant = newColor
        "secondaryVariant" -> themeColors.secondaryVariant = newColor
        "background" -> themeColors.background = newColor
        "surface" -> themeColors.surface = newColor
        "error" -> themeColors.error = newColor
        "onPrimary" -> themeColors.onPrimary = newColor
        "onSecondary" -> themeColors.onSecondary = newColor
        "onBackground" -> themeColors.onBackground = newColor
        "onSurface" -> themeColors.onSurface = newColor
        "onError" -> themeColors.onError = newColor
    }
}

@Preview
@Composable
fun CreateThemePreview() {
    ThemeItTheme {
        CreateTheme()
    }
}