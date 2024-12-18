package com.example.labfourjetpack.calculator1

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

import kotlin.math.sqrt

@Composable
fun Calculator1(
    goBack: () -> Unit
) {
    var iK by remember { mutableStateOf("") }
    var tF by remember { mutableStateOf("") }
    var pot by remember { mutableStateOf("") }
    var sM by remember { mutableStateOf("") }
    var tM by remember { mutableStateOf("") }
    var s by remember { mutableStateOf(0.0) }
    var sMin by remember { mutableStateOf(0.0) }


    fun calculate() {
        val formattedIk = iK.toDoubleOrNull() ?: 0.0
        val formattedTf = tF.toDoubleOrNull() ?: 0.0
        val formattedPot = tF.toDoubleOrNull() ?: 0.0
        val formattedSm = sM.toDoubleOrNull() ?: 0.0
        val formattedTm = tM.toDoubleOrNull() ?: 0.0
        val u = 10.0

        val iM = (formattedSm / 2.0) / (sqrt(3.0) * u)
        val iMPA = 2.0 * iM
        var jEK = 0.0

        // Значення економічної густини струму
        if (formattedTm >= 1000 && formattedTm < 3000) {
            jEK = 1.6
        } else if (formattedTm >= 3000 && formattedTm <= 5000) {
            jEK = 1.4
        } else {
            jEK = 1.2
        }
        s = iM / jEK
        sMin = (formattedIk * 1000 * sqrt(formattedTf)) / 92.0
    }

    Column(modifier = Modifier.padding(all = 15.dp)) {
        TextField(
            value = iK,
            onValueChange = { iK = it },
            label = { Text("Струм КЗ, кА") },
            maxLines = 1,
        )
        TextField(
            value = tF,
            onValueChange = { tF = it },
            label = { Text("Фіктивний чай вимикання струму КЗ, с") },
            maxLines = 1,
        )
        TextField(
            value = sM,
            onValueChange = { sM = it },
            label = { Text("Sm, кВ") },
            maxLines = 1,
        )
        TextField(
            value = tM,
            onValueChange = { tM = it },
            label = { Text("Tm, год") },
            maxLines = 1,
        )
        Button(
            onClick = { calculate() }
        ) {
            Text("Calculate")
        }
        if (s != 0.0 && sMin != 0.0 && s >= sMin) {
            Text("Переріз жил кабелю: $s мм.")
        }
        if (s != 0.0 && sMin != 0.0 && s < sMin) {
            Text("Переріз жил кабелю $s та має бути збільшений мінімум до $sMin мм.")

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