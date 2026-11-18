package com.example.imcapp.composables.features.imc

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.imcapp.ui.theme.imcAccent
import com.example.imcapp.ui.theme.imcBackground
import com.example.imcapp.ui.theme.imcBajoPeso
import com.example.imcapp.ui.theme.imcNormal
import com.example.imcapp.ui.theme.imcObesidad
import com.example.imcapp.ui.theme.imcSobrepeso
import com.example.imcapp.ui.theme.imcSurface
import com.example.imcapp.ui.theme.imcTextSecondary
import java.util.Locale

/** Rangos de la OMS: cada uno con su título, descripción y color */
enum class IMCCategory(val title: String, val description: String, val color: Color) {
    BAJO_PESO(
        "BAJO PESO",
        "Tu peso está por debajo de lo recomendado. Puede ser buena idea consultarlo con un profesional.",
        imcBajoPeso
    ),
    NORMAL(
        "PESO NORMAL",
        "¡Enhorabuena! Tu peso está dentro del rango saludable. Sigue así.",
        imcNormal
    ),
    SOBREPESO(
        "SOBREPESO",
        "Tu peso está algo por encima de lo recomendado. Más actividad física y una dieta equilibrada ayudan.",
        imcSobrepeso
    ),
    OBESIDAD(
        "OBESIDAD",
        "Tu peso está bastante por encima de lo recomendado. Te aconsejamos consultarlo con un profesional.",
        imcObesidad
    )
}

fun getIMCCategory(imc: Double): IMCCategory = when {
    imc < 18.5 -> IMCCategory.BAJO_PESO
    imc < 25.0 -> IMCCategory.NORMAL
    imc < 30.0 -> IMCCategory.SOBREPESO
    else -> IMCCategory.OBESIDAD
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun IMCResultScreen(result: Double, onRecalculate: () -> Unit = {}) {
    val category = getIMCCategory(result)

    Scaffold(
        containerColor = imcBackground,
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Tu resultado", fontWeight = FontWeight.Bold) },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = imcBackground,
                    titleContentColor = Color.White
                )
            )
        },
        bottomBar = {
            Button(
                onClick = onRecalculate,
                modifier = Modifier
                    .navigationBarsPadding()
                    .padding(16.dp)
                    .fillMaxWidth()
                    .height(60.dp),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = imcAccent,
                    contentColor = Color.White
                )
            ) {
                Text("VOLVER A CALCULAR", fontSize = 20.sp, fontWeight = FontWeight.Bold, letterSpacing = 2.sp)
            }
        }
    ) { paddingValues ->
        Card(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
                .fillMaxSize(),
            shape = RoundedCornerShape(24.dp),
            colors = CardDefaults.cardColors(containerColor = imcSurface)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                // Etiqueta de la categoría con su color
                Text(
                    category.title,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 2.sp,
                    color = category.color
                )

                // Valor del IMC en grande, con 1 decimal
                Text(
                    String.format(Locale.getDefault(), "%.1f", result),
                    fontSize = 96.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = Color.White
                )

                IMCScaleBar(imc = result)

                Text(
                    category.description,
                    fontSize = 18.sp,
                    color = imcTextSecondary,
                    textAlign = TextAlign.Center,
                    lineHeight = 26.sp
                )
            }
        }
    }
}

/** Barra de 4 colores (15 → 40) con un marcador donde cae el IMC */
@Composable
fun IMCScaleBar(imc: Double) {
    val min = 15.0
    val max = 40.0
    val fraction = ((imc - min) / (max - min)).coerceIn(0.0, 1.0).toFloat()
    // Peso de cada tramo según su anchura en la escala 15-40
    val tramos = listOf(
        3.5f to imcBajoPeso,   // 15 - 18.5
        6.5f to imcNormal,     // 18.5 - 25
        5f to imcSobrepeso,    // 25 - 30
        10f to imcObesidad     // 30 - 40
    )

    Column(modifier = Modifier.fillMaxWidth()) {
        BoxWithConstraints(modifier = Modifier.fillMaxWidth().height(20.dp)) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(10.dp)
                    .align(Alignment.Center)
                    .clip(RoundedCornerShape(50))
            ) {
                tramos.forEach { (peso, color) ->
                    Box(Modifier.weight(peso).fillMaxSize().background(color))
                }
            }
            // Marcador
            Box(
                modifier = Modifier
                    .offset(x = (maxWidth - 20.dp) * fraction)
                    .size(20.dp)
                    .clip(CircleShape)
                    .background(Color.White)
            )
        }
        Spacer(Modifier.height(6.dp))
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            listOf("15", "18.5", "25", "30", "40").forEach {
                Text(it, fontSize = 12.sp, color = imcTextSecondary)
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun IMCResultScreenPreview() {
    IMCResultScreen(result = 22.5)
}
