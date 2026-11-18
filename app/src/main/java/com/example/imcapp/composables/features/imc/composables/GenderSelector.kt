package com.example.imcapp.composables.features.imc.composables

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.imcapp.ui.theme.colorSelectedCard
import com.example.imcapp.ui.theme.colorUnSelectedCard
import com.example.imcapp.ui.theme.imcTextSecondary
import androidx.compose.runtime.getValue

/**
 * Selector de género: dos [GenderCard] en una fila, cada una con la mitad del ancho.
 *
 * @param isMaleSelected `true` si está seleccionado «Hombre».
 * @param onGenderSelected recibe `true` al pulsar «Hombre» y `false` al pulsar «Mujer».
 */
@Preview
@Composable
fun GenderSelector(
    isMaleSelected: Boolean = true,
    onGenderSelected: (Boolean) -> Unit = {}
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        GenderCard(
            modifier = Modifier.weight(1f),
            gender = "Hombre",
            simbolo = "♂",
            isSelected = isMaleSelected,
            onClick = { onGenderSelected(true) }
        )
        GenderCard(
            modifier = Modifier.weight(1f),
            gender = "Mujer",
            simbolo = "♀",
            isSelected = !isMaleSelected, // negamos: mujer no seleccionada al inicio
            onClick = { onGenderSelected(false) }
        )
    }
}

/**
 * Tarjeta de un género con su símbolo y su nombre.
 *
 * Usa `Card` con `onClick` (respeta la forma redondeada y el efecto ripple).
 * Al seleccionarla, el color cambia con `animateColorAsState` y se añaden
 * un borde blanco y más elevación.
 *
 * @param modifier modificador de la tarjeta (su peso en la fila).
 * @param gender nombre del género («Hombre» o «Mujer»).
 * @param simbolo símbolo que se muestra en grande.
 * @param isSelected si la tarjeta está seleccionada.
 * @param onClick acción al pulsarla.
 */
@Composable
fun GenderCard(
    modifier: Modifier,
    gender: String,
    simbolo: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    // El color cambia con una pequeña animación al seleccionar
    val colorContenedor by animateColorAsState(
        targetValue = if (isSelected) colorSelectedCard else colorUnSelectedCard,
        label = "colorGenero"
    )

    // Card con onClick propio (mejor que Modifier.clickable: respeta la forma y el ripple)
    Card(
        onClick = onClick,
        modifier = modifier.height(150.dp),
        shape = RoundedCornerShape(20.dp),
        border = if (isSelected) BorderStroke(2.dp, Color.White) else null,
        colors = CardDefaults.cardColors(
            containerColor = colorContenedor,
            contentColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = if (isSelected) 8.dp else 2.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(simbolo, fontSize = 64.sp, fontWeight = FontWeight.ExtraBold)
            Text(
                gender.uppercase(),
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 1.5.sp,
                color = if (isSelected) Color.White else imcTextSecondary
            )
        }
    }
}
