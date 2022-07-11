package com.rafaeltamayo.themeit.presentation.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.NonRestartableComposable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterialApi::class)
@Composable
@NonRestartableComposable
fun ColorCard(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    backgroundColor: Color = MaterialTheme.colors.surface,
    elevation: Dp = 1.dp,
    title: String,
    colorCode: String,
) {
    Card(
        onClick = onClick,
        modifier = modifier.padding(2.dp).fillMaxWidth(),
        backgroundColor = backgroundColor,
        elevation = elevation,
    ) {
        Column(
            modifier = Modifier,
            verticalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(
                text = title,
                modifier = Modifier
                    .padding(start = 4.dp, top = 4.dp)
            )
            Text(
                text = colorCode,
                modifier = Modifier
                    .align(Alignment.End)
                    .padding(end = 4.dp, bottom = 4.dp),
            )
        }
    }
}

@Composable
fun LargeColorCard(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    backgroundColor: Color,
    title: String,
    colorCode: String,
) {
    ColorCard(
        onClick = onClick,
        modifier = modifier.height(125.dp),
        backgroundColor = backgroundColor,
        title = title,
        colorCode = colorCode
    )
}

@Composable
fun MediumColorCard(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    backgroundColor: Color,
    title: String,
    colorCode: String,
) {
    ColorCard(
        onClick = onClick,
        modifier = modifier.height(100.dp),
        backgroundColor = backgroundColor,
        title = title,
        colorCode = colorCode
    )
}


@Preview
@Composable
fun ColorCardPreview() {
    ColorCard(
        onClick = {},
        backgroundColor = Color.Cyan,
        title = "Primary Color",
        colorCode = "xxxxxx"
    )
}