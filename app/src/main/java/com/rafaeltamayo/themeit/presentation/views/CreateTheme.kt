package com.rafaeltamayo.themeit.presentation.views

import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.rafaeltamayo.themeit.presentation.ui.components.ColorPickerDialog
import com.rafaeltamayo.themeit.presentation.ui.components.LargeColorCard
import com.rafaeltamayo.themeit.presentation.ui.components.MediumColorCard
import com.rafaeltamayo.themeit.presentation.ui.theme.*

@Composable
fun CreateTheme() {
    val showDialog = remember { mutableStateOf(false) }
    var currentColorCard = remember { mutableStateOf(Purple500) }
    Column {
        Row(modifier = Modifier.padding(2.dp)) {
            Column(modifier = Modifier.weight(1f)) {
                LargeColorCard(
                    onClick = { showDialog.value = true },
                    backgroundColor = currentColorCard.value,
                    title = "Primary Color",
                    colorCode = "0xFF6200EE"
                )
            }
            Column(modifier = Modifier.weight(1f)) {
                LargeColorCard(
                    onClick = {},
                    backgroundColor = Teal200,
                    title = "Secondary Color",
                    colorCode = "0xFF03DAC5"
                )
            }
        }
        Row(modifier = Modifier.padding(2.dp)) {
            Column(modifier = Modifier.weight(1f)) {
                MediumColorCard(
                    onClick = {},
                    backgroundColor = Purple700,
                    title = "Primary Variant",
                    colorCode = "0xFF3700B3"
                )
            }
            Column(modifier = Modifier.weight(1f)) {
                MediumColorCard(
                    onClick = {},
                    backgroundColor = Teal700,
                    title = "Secondary Variant",
                    colorCode = "0xFF027C6F"
                )
            }
        }
        Divider()
        Row(modifier = Modifier.padding(2.dp)) {
            Column(modifier = Modifier.weight(1f)) {
                MediumColorCard(
                    onClick = {},
                    backgroundColor = White,
                    title = "Background",
                    colorCode = "0xFFFFFFFF"
                )
            }
            Column(modifier = Modifier.weight(1f)) {
                MediumColorCard(
                    onClick = {},
                    backgroundColor = White,
                    title = "Surface",
                    colorCode = "0xFFFFFFFF"
                )
            }
            Column(modifier = Modifier.weight(1f)) {
                MediumColorCard(
                    onClick = {},
                    backgroundColor = Red,
                    title = "Error",
                    colorCode = "0xFFB00020"
                )
            }
        }
        Divider()
        Row(modifier = Modifier.padding(2.dp)) {
            Column(modifier = Modifier.weight(1f)) {
                MediumColorCard(
                    onClick = {},
                    backgroundColor = White,
                    title = "On Primary",
                    colorCode = "0xFFFFFFFF"
                )
            }
            Column(modifier = Modifier.weight(1f)) {
                MediumColorCard(
                    onClick = {},
                    backgroundColor = White,
                    title = "On Secondary",
                    colorCode = "0xFFFFFFFF"
                )
            }
        }
        Row(modifier = Modifier.padding(2.dp)) {
            Column(modifier = Modifier.weight(1f)) {
                MediumColorCard(
                    onClick = {},
                    backgroundColor = White,
                    title = "On Background",
                    colorCode = "0xFFFFFFFF"
                )
            }
            Column(modifier = Modifier.weight(1f)) {
                MediumColorCard(
                    onClick = {},
                    backgroundColor = White,
                    title = "On Surface",
                    colorCode = "0xFFFFFFFF"
                )
            }
            Column(modifier = Modifier.weight(1f)) {
                MediumColorCard(
                    onClick = {},
                    backgroundColor = White,
                    title = "On Error",
                    colorCode = "0xFFB00020"
                )
            }
        }
    }
    if(showDialog.value) {
        var showedColor = Color.Black
        ColorPickerDialog(
            onDismissRequest = {
                showDialog.value = false
                currentColorCard.value = showedColor
                               },
            onColorChanged = {
                showedColor= it.toColor()
            }
        )
    }
}