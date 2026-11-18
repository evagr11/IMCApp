package com.example.imcapp.composables.features.imc.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.IconButtonDefaults
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
import com.example.imcapp.ui.theme.imcBackground
import com.example.imcapp.ui.theme.imcTextSecondary

/**
 * Contadores de peso y edad: dos [CounterCard] en una fila.
 *
 * Los límites de los valores se controlan en
 * [com.example.imcapp.composables.features.imc.IMCScreen].
 *
 * @param age edad actual en años.
 * @param onPlusAgeSelected se llama al pulsar + en la edad.
 * @param onMinusAgeSelected se llama al pulsar − en la edad.
 * @param weight peso actual en kilogramos.
 * @param onPlusWeightSelected se llama al pulsar + en el peso.
 * @param onMinusWeightSelected se llama al pulsar − en el peso.
 */
@Composable
fun CounterSelector(
    age: Int,
    onPlusAgeSelected: () -> Unit,
    onMinusAgeSelected: () -> Unit,
    weight: Int,
    onPlusWeightSelected: () -> Unit,
    onMinusWeightSelected: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        CounterCard(
            modifier = Modifier.weight(1f),
            title = "PESO",
            value = weight,
            unit = "kg",
            onPlus = onPlusWeightSelected,
            onMinus = onMinusWeightSelected
        )
        CounterCard(
            modifier = Modifier.weight(1f),
            title = "EDAD",
            value = age,
            unit = "años", // antes salía "kg" también en la edad
            onPlus = onPlusAgeSelected,
            onMinus = onMinusAgeSelected
        )
    }
}

/**
 * Tarjeta de contador con título, valor, unidad y botones − y +.
 *
 * @param modifier modificador de la tarjeta (su peso en la fila).
 * @param title título en mayúsculas («PESO» o «EDAD»).
 * @param value valor que se muestra.
 * @param unit unidad que acompaña al valor («kg» o «años»).
 * @param onPlus acción del botón +.
 * @param onMinus acción del botón −.
 */
@Preview
@Composable
fun CounterCard(
    modifier: Modifier = Modifier,
    title: String = "PESO",
    value: Int = 65,
    unit: String = "kg",
    onPlus: () -> Unit = {},
    onMinus: () -> Unit = {}
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = colorUnSelectedCard)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 20.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                title,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 1.5.sp,
                color = imcTextSecondary
            )
            Row(verticalAlignment = Alignment.Bottom) {
                Text(
                    value.toString(),
                    fontSize = 44.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = Color.White
                )
                Text(
                    " $unit",
                    fontSize = 14.sp,
                    color = imcTextSecondary,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
            }
            Row(horizontalArrangement = Arrangement.spacedBy(20.dp)) {
                RoundCounterButton(text = "−", onClick = onMinus)
                RoundCounterButton(text = "+", onClick = onPlus)
            }
        }
    }
}

/**
 * Botón circular de 48 dp para sumar o restar.
 *
 * @param text símbolo del botón («+» o «−»).
 * @param onClick acción al pulsarlo.
 */
@Composable
fun RoundCounterButton(text: String, onClick: () -> Unit) {
    FilledIconButton(
        onClick = onClick,
        modifier = Modifier.size(48.dp),
        shape = CircleShape,
        colors = IconButtonDefaults.filledIconButtonColors(
            containerColor = imcBackground,
            contentColor = Color.White
        )
    ) {
        Text(text, fontSize = 26.sp, fontWeight = FontWeight.Bold)
    }
}
