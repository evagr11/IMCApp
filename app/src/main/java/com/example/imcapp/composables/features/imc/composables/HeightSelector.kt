package com.example.imcapp.composables.features.imc.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.imcapp.ui.theme.colorUnSelectedCard
import com.example.imcapp.ui.theme.imcAccent
import com.example.imcapp.ui.theme.imcTextSecondary

/**
 * Tarjeta para elegir la altura entre 120 y 220 cm con un `Slider`.
 *
 * El estado vive en [com.example.imcapp.composables.features.imc.IMCScreen]
 * (state hoisting): la tarjeta solo muestra el valor y avisa cuando cambia.
 *
 * @param height altura actual en centímetros.
 * @param onHeightChange recibe la nueva altura al mover el `Slider`.
 */
@Composable
fun HeightSelector(height: Float, onHeightChange: (Float) -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = colorUnSelectedCard)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(
                "ALTURA",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 1.5.sp,
                color = imcTextSecondary
            )
            // Número grande + unidad pequeña alineados por la línea base
            Row(verticalAlignment = Alignment.Bottom) {
                Text(
                    height.toInt().toString(),
                    fontSize = 52.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = Color.White
                )
                Text(
                    " cm",
                    fontSize = 18.sp,
                    color = imcTextSecondary,
                    modifier = Modifier.padding(bottom = 10.dp)
                )
            }
            Slider(
                value = height,
                onValueChange = onHeightChange,
                valueRange = 120f..220f,
                colors = SliderDefaults.colors(
                    thumbColor = imcAccent,
                    activeTrackColor = imcAccent,
                    inactiveTrackColor = imcTextSecondary.copy(alpha = 0.3f)
                )
            )
        }
    }
}

/** Vista previa de [HeightSelector] con 170 cm. */
@Preview
@Composable
fun HeightSelectorPreview() {
    HeightSelector(height = 170f, onHeightChange = {})
}
