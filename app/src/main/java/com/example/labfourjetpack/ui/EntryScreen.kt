package com.example.labfourjetpack.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

val buttonStyles = Modifier.padding(all = 30.dp).background(color = Color.White)

@Composable
fun EntryScreen(
    onCalculator1Navigate: () -> Unit,
    onCalculator2Navigate: () -> Unit,
    onCalculator3Navigate: () -> Unit,
) {
    Column(modifier = buttonStyles) {
        Button(
            onClick = { onCalculator1Navigate() },
        ) {
            Text(
                text = "Go to calculator 1"
            )
        }
        Button(
            onClick = onCalculator2Navigate
        ) {
            Text(
                text = "Go to calculator 2"
            )
        }
        Button(
            onClick = onCalculator3Navigate
        ) {
            Text(
                text = "Go to calculator 3"
            )
        }
    }
}