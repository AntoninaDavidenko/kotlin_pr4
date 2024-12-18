package com.example.labfourjetpack.calculator3

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp import kotlin.math.pow

import kotlin.math.sqrt
import kotlin.math.roundToInt

@Composable
fun Calculator3(
    goBack: () -> Unit
) {
    var rCN by remember { mutableStateOf("") }
    var xCN by remember { mutableStateOf("") }
    var rCMin by remember { mutableStateOf("") }
    var xCMin by remember { mutableStateOf("") }

    var i3Sh by remember { mutableDoubleStateOf(0.0) }
    var i3ShMin by remember { mutableDoubleStateOf(0.0) }
    var i2Sh by remember { mutableDoubleStateOf(0.0) }
    var i2ShMin by remember { mutableDoubleStateOf(0.0) }

    var i3ShN by remember { mutableDoubleStateOf(0.0) }
    var i3ShNMin by remember { mutableDoubleStateOf(0.0) }
    var i2ShN by remember { mutableDoubleStateOf(0.0) }
    var i2ShNMin by remember { mutableDoubleStateOf(0.0) }
    var i3LN by remember { mutableDoubleStateOf(0.0) }
    var i3LNMin by remember { mutableDoubleStateOf(0.0) }
    var i2LN by remember { mutableDoubleStateOf(0.0) }
    var i2LNMin by remember { mutableDoubleStateOf(0.0) }


    fun calculate3() {
        val formattedRCN = rCN.toDoubleOrNull() ?: 0.0
        val formattedRCMin = rCMin.toDoubleOrNull() ?: 0.0
        val formattedXCN = xCN.toDoubleOrNull() ?: 0.0
        val formattedXCMin = xCMin.toDoubleOrNull() ?: 0.0

        val uMax = 11.1
        val uVN = 115.0
        val sNom = 6.3

        val uNN = 11.0

        val xT = (uMax * uVN.pow(2.0)) / (100 * sNom)
        val rSh = formattedRCN
        val xSh = formattedXCN + xT
        val zSh = sqrt(rSh.pow(2.0) + xSh.pow(2.0))
        val rShMin = formattedRCMin
        val xShMin = formattedXCMin + xT
        val zShMin = sqrt(rShMin.pow(2.0) + xShMin.pow(2.0))
        // Струми 2 і 3 фазного КЗ на шинах 10 кВ при нормальному та мінімальному режимах, приведені
        // до напруги 110 кВ
        i3Sh = (uVN * 1000) / (1.73 * zSh)
        i2Sh = i3Sh * (1.73 / 2)
        i3ShMin =  (uVN * 1000) / (1.73 * zShMin)
        i2ShMin = i3ShMin * (1.73 / 2)

        var kPR = uNN.pow(2.0) / uVN.pow(2.0)
        kPR = (kPR * 1000).roundToInt() / 1000.0

        val rShN = rSh * kPR
        val xShN = xSh * kPR
        val zShN = sqrt( rShN.pow(2.0) + xShN.pow(2.0) )
        val rShNMin = rShMin * kPR
        val xShNMin = xShMin * kPR
        val zShNMin = sqrt( rShNMin.pow(2.0) + xShNMin.pow(2.0) )
        // Дійсні струми 2 і 3 фазного КЗ на шинах 10 кВ при нормальному та мінімальному режимах
        i3ShN = (uNN * 1000.0) / (1.73 * zShN)
        i2ShN = i3ShN * (1.73 / 2)
        i3ShNMin = (uNN * 1000.0) / (1.73 * zShNMin)
        i2ShNMin = i3ShNMin * (1.73 / 2)

        val l = 12.37
        val rL = l * 0.64
        val xL = l * 0.363

        val rEN = rL + rShN
        val xEN = xL + xShN
        val zEN = sqrt( rEN.pow(2.0) + xEN.pow(2.0) )
        val rENMin = rL + rShNMin
        val xENMin = xL + xShNMin
        val zENMin = sqrt( rENMin.pow(2.0) + xENMin.pow(2.0) )
        // Струми 2 і 3 фазного КЗ в точці 10 при нормальному та мінімальному режимах
        i3LN = (uNN * 1000) / (1.73 * zEN)
        i2LN = i3LN * (1.73 / 2.0)
        i3LNMin = (uNN * 1000) / (1.73 * zENMin)
        i2LNMin = i3LNMin * (1.73 / 2.0)

    }

    Column(modifier = Modifier.padding(all = 15.dp)) {
        TextField(
            value = rCN,
            onValueChange = { rCN = it },
            label = { Text("Rс.н, Ом") },
            maxLines = 1,
        )
        TextField(
            value = xCN,
            onValueChange = { xCN = it },
            label = { Text("Хс.н, Ом") },
            maxLines = 1,
        )
        TextField(
            value = rCMin,
            onValueChange = { rCMin = it },
            label = { Text("Rс.min, Ом") },
            maxLines = 1,
        )
        TextField(
            value = xCMin,
            onValueChange = { xCMin = it },
            label = { Text("Xс.min, Ом") },
            maxLines = 1,
        )
        Button(
            onClick = { calculate3() }
        ) {
            Text("Calculate")
        }
        if (i2LN != 0.0 && i2LNMin != 0.0) {
            Text("Струми трифазного та двофазного КЗ на шинах 10кВ в нормальному та мінімальному" +
                    " режимах, приведені до напруги 110 кВ: \n" +
                    "Опір трифазного кз: нормальний: $i3Sh А, мінімальний: $i3ShMin А \n" +
                    "Опір двофазного кз: нормальний: $i2Sh А, мінімальний: $i2ShMin А \n" +
                    "Дійсні струми трифазного та двофазного КЗ на шинах 10кВ в нормальному та" +
                    " мінімальному режимах: \n" +
                    "Опір трифазного кз: нормальний: $i3ShN А, мінімальний: $i3ShNMin А \n" +
                    "Опір двофазного кз: нормальний: $i2ShN А, мінімальний: $i2ShNMin А \n" +
                    "Струми трифазного та двофазного КЗ в точці 10 в нормальному та" +
                    " мінімальному режимах: \n" +
                    "Опір трифазного кз: нормальний: $i3LN А, мінімальний: $i3LNMin А \n" +
                    "Опір двофазного кз: нормальний: $i2LN А, мінімальний: $i2LNMin А \n" +
                    "Аварійний режим на цій підстанції не передбачений")
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