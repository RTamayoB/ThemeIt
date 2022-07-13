package com.rafaeltamayo.themeit.presentation.ui.components

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.rafaeltamayo.themeit.presentation.ui.components.colorpicker.ColorGradient
import com.rafaeltamayo.themeit.presentation.ui.components.colorpicker.HSVColor
import com.rafaeltamayo.themeit.presentation.ui.components.colorpicker.HueBar

@Composable
fun ColorPickerDialog(
    onDismissRequest: () -> Unit,
    onColorChanged: (HSVColor) -> Unit
) {
    val hsvColor = rememberSaveable(stateSaver = HSVColor.Saver) { mutableStateOf(HSVColor.from(color = Color.Cyan)) }
    Dialog(
        onDismissRequest = onDismissRequest
    ) {
        Card(
            modifier = Modifier.size(300.dp)
        ) {
            Column {
                Row(
                    Modifier.weight(.6f)
                ) {
                    Column(
                        modifier = Modifier.weight(.8f)
                    ) {
                        ColorGradient(
                            currentColor = hsvColor.value,
                            onColorGradientChanged = { saturation, value ->
                                hsvColor.value = hsvColor.value.copy(saturation = saturation, value = value)
                                onColorChanged(hsvColor.value)
                            }
                        )
                    }
                    Column(
                        modifier = Modifier.weight(.2f)
                    ) {
                        HueBar(
                            hueColor = hsvColor.value,
                            onHueChanged = { newHue ->
                                hsvColor.value = hsvColor.value.copy(hue = newHue)
                                onColorChanged(hsvColor.value)
                            }
                        )
                    }

                }
                Row(Modifier.weight(.4f)) {
                    Material500Button(hsvColor.value.toColor())
                }

            }
        }
    }
}

@Composable
fun Material500Button(color: Color) {
    Button(
        onClick = {  },
        modifier = Modifier.size(40.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = color
        )
    ) {

    }
}

@Preview
@Composable
fun ColorPickerDialogPreview() {
    ColorPickerDialog(
        onDismissRequest = {},
        onColorChanged = {}
    )
}