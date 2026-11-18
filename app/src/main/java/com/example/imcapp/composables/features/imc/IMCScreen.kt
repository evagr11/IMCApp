package com.example.imcapp.composables.features.imc

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.imcapp.composables.features.imc.composables.CounterSelector
import com.example.imcapp.composables.features.imc.composables.GenderSelector
import com.example.imcapp.composables.features.imc.composables.HeightSelector
import com.example.imcapp.ui.theme.imcAccent
import com.example.imcapp.ui.theme.imcBackground
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun IMCScreen(
    onCalculate: (Double) -> Unit = {},
    onBack: () -> Unit = {}
) {
    // rememberSaveable: los valores se conservan al girar la pantalla
    // y al volver desde IMCResultScreen
    var isMaleSelected by rememberSaveable { mutableStateOf(true) }
    var height by rememberSaveable { mutableFloatStateOf(170f) }
    var age by rememberSaveable { mutableIntStateOf(20) }
    var weight by rememberSaveable { mutableIntStateOf(65) }

    Scaffold(
        containerColor = imcBackground,
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Calculadora IMC", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = onBack) { Text("←", fontSize = 24.sp) }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = imcBackground,
                    titleContentColor = Color.White,
                    navigationIconContentColor = Color.White
                )
            )
        },
        bottomBar = {
            CalculateButton(
                modifier = Modifier
                    .navigationBarsPadding()
                    .padding(16.dp),
                onClick = { onCalculate(calculateIMC(weight, height)) }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            GenderSelector(
                isMaleSelected = isMaleSelected,
                onGenderSelected = { isMaleSelected = it }
            )

            HeightSelector(height = height, onHeightChange = { height = it })

            CounterSelector(
                age = age,
                onPlusAgeSelected = { if (age < 120) age++ },
                onMinusAgeSelected = { if (age > 1) age-- },
                weight = weight,
                onPlusWeightSelected = { if (weight < 300) weight++ },
                onMinusWeightSelected = { if (weight > 20) weight-- }
            )
        }
    }
}

@Composable
fun CalculateButton(modifier: Modifier = Modifier, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .height(60.dp),
        shape = RoundedCornerShape(16.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = imcAccent,
            contentColor = Color.White
        )
    ) {
        Text("CALCULAR", fontSize = 20.sp, fontWeight = FontWeight.Bold, letterSpacing = 2.sp)
    }
}

/** IMC = peso (kg) / altura (m)² */
fun calculateIMC(weight: Int, heightCm: Float): Double {
    val heightM = heightCm / 100.0
    return weight / (heightM * heightM)
}

@Preview(showSystemUi = true)
@Composable
fun IMCScreenPreview() {
    IMCScreen()
}
