package com.example.labfourjetpack.calculator2

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlin.math.pow
import kotlin.math.sqrt

@Composable
fun Calculator2(
    goBack: () -> Unit
) {
    var s by remember { mutableStateOf("") }
    var result by remember { mutableStateOf("") }

    fun calculate2() {
        val formattedS = s.toDoubleOrNull() ?: 0.0
        val uSN = 10.5
        val uK = 10.5
        val sNom = 6.3

        val xC = uSN.pow(2.0) / formattedS
        val xT = (uK / 100.0) * (uSN.pow(2.0) / sNom)

        val xSum = xC + xT
        val iP0 = uSN / (sqrt(3.0) * xSum)

        result = (iP0).toString()
    }

    Column(modifier = Modifier.padding(all = 15.dp)) {
        TextField(
            value = s,
            onValueChange = { s = it },
            label = { Text("Потужність") },
            maxLines = 1,
        )
        Button(
            onClick = { calculate2() }
        ) {
            Text("Calculate")
        }
        if (result.isNotEmpty()) {
            Text("Початкове діюче значення струму трифазного КЗ: $result кА.")
        }
        Box(
            modifier = Modifier.padding(top = 100.dp)
        ) {
            Button(
                onClick = goBack
            ) {
                Text("Go back")
            }
        }
    }
}