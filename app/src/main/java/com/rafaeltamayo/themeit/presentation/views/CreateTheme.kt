package com.rafaeltamayo.themeit.presentation.views

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rafaeltamayo.themeit.data.ThemeColors
import com.rafaeltamayo.themeit.presentation.ui.components.ColorPickerDialog
import com.rafaeltamayo.themeit.presentation.ui.components.LargeColorCard
import com.rafaeltamayo.themeit.presentation.ui.components.MediumColorCard
import com.rafaeltamayo.themeit.presentation.ui.theme.*
import com.rafaeltamayo.themeit.presentation.viewmodels.CreateThemeViewModel
import androidx.lifecycle.viewmodel.compose.viewModel

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
fun CreateTheme(
    viewModel: CreateThemeViewModel = viewModel()
) {

    LaunchedEffect(key1 = true) {
        viewModel.fetchTheme()
    }

    val uiState = viewModel.uiState.collectAsState()

    val themeColors = uiState.value.colors
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
                backgroundColor = themeColors?.primary ?: Color.Black,
                title = "Primary Color",
                colorCode = "0xFF6200EE"
            )
            LargeColorCard(
                modifier = Modifier.weight(1.0F),
                onClick = {
                    showDialog.value = true
                    currentColorCard.value = "secondary"
                          },
                backgroundColor = themeColors?.secondary ?: Color.Black,
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
                backgroundColor = themeColors?.primaryVariant ?: Color.Black,
                title = "Primary Variant",
                colorCode = "0xFF3700B3"
            )
            MediumColorCard(
                modifier = Modifier.weight(1.0F),
                onClick = {
                    showDialog.value = true
                    currentColorCard.value = "secondaryVariant"
                },
                backgroundColor = themeColors?.secondaryVariant ?: Color.Black,
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
                backgroundColor = themeColors?.background ?: Color.Black,
                title = "Background",
                colorCode = "0xFFFFFFFF"
            )
            MediumColorCard(
                modifier = Modifier.weight(1.0F),
                onClick = {
                    showDialog.value = true
                    currentColorCard.value = "surface"
                },
                backgroundColor = themeColors?.surface ?: Color.Black,
                title = "Surface",
                colorCode = "0xFFFFFFFF"
            )
            MediumColorCard(
                modifier = Modifier.weight(1.0F),
                onClick = {
                    showDialog.value = true
                    currentColorCard.value = "error"
                },
                backgroundColor = themeColors?.error ?: Color.Black,
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
                backgroundColor = themeColors?.onPrimary ?: Color.Black,
                title = "On Primary",
                colorCode = "0xFFFFFFFF"
            )
            MediumColorCard(
                modifier = Modifier.weight(1.0F),
                onClick = {
                    showDialog.value = true
                    currentColorCard.value = "onSecondary"
                },
                backgroundColor = themeColors?.onSecondary ?: Color.Black,
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
                backgroundColor = themeColors?.onBackground ?: Color.Black,
                title = "On Background",
                colorCode = "0xFFFFFFFF"
            )
            MediumColorCard(
                modifier = Modifier.weight(1.0F),
                onClick = {
                    showDialog.value = true
                    currentColorCard.value = "onSurface"
                },
                backgroundColor = themeColors?.onSurface ?: Color.Black,
                title = "On Surface",
                colorCode = "0xFFFFFFFF"
            )
            MediumColorCard(
                modifier = Modifier.weight(1.0F),
                onClick = {
                    showDialog.value = true
                    currentColorCard.value = "onError"
                },
                backgroundColor = themeColors?.onError ?: Color.Black,
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
                setSelectedCard(currentColorCard.value, themeColors, showedColor)
                               },
            onColorChanged = {
                showedColor= it.toColor()
            }
        )
    }
}

private fun setSelectedCard(card: String, themeColors: ThemeColors?, newColor: Color) {
    when(card) {
        "primary" -> themeColors?.primary = newColor
        "secondary" -> themeColors?.secondary = newColor
        "primaryVariant" -> themeColors?.primaryVariant = newColor
        "secondaryVariant" -> themeColors?.secondaryVariant = newColor
        "background" -> themeColors?.background = newColor
        "surface" -> themeColors?.surface = newColor
        "error" -> themeColors?.error = newColor
        "onPrimary" -> themeColors?.onPrimary = newColor
        "onSecondary" -> themeColors?.onSecondary = newColor
        "onBackground" -> themeColors?.onBackground = newColor
        "onSurface" -> themeColors?.onSurface = newColor
        "onError" -> themeColors?.onError = newColor
    }
}

@Preview
@Composable
fun CreateThemePreview() {
    ThemeItTheme {
        CreateTheme()
    }
}