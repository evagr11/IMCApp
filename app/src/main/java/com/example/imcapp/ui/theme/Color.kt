package com.example.imcapp.ui.theme

import androidx.compose.ui.graphics.Color

/*
 * Colores de IMCApp.
 *
 * Los colores `imc*` y `color*Card` son los de la versión mejorada: todas las
 * pantallas los toman de aquí. Los `Purple`/`Pink` son los que crea Android
 * Studio con el proyecto y los usa `Theme.kt`.
 */
// --- Colores de la IMCApp (versión mejorada) ---
/** Fondo general de las pantallas (azul noche). */
val imcBackground = Color(0xFF0B1020)      // fondo general (azul noche)
/** Fondo de las tarjetas. */
val imcSurface = Color(0xFF1C2238)         // tarjetas no seleccionadas
/** Tarjeta de género seleccionada (azul). */
val colorSelectedCard = Color(0xFF4287F5)  // tarjeta seleccionada (azul)
/** Tarjetas no seleccionadas y tarjetas de altura, peso y edad. */
val colorUnSelectedCard = imcSurface
/** Color de acento: botones CALCULAR y VOLVER A CALCULAR y el Slider. */
val imcAccent = Color(0xFFFF4F6D)          // botón principal (calcular / recalcular)
/** Textos secundarios: títulos de las tarjetas, unidades y descripciones. */
val imcTextSecondary = Color(0xFF9AA3C0)   // textos secundarios

// Colores de cada rango de IMC
/** Color de la categoría bajo peso. */
val imcBajoPeso = Color(0xFF4FC3F7)
/** Color de la categoría peso normal. */
val imcNormal = Color(0xFF4CAF50)
/** Color de la categoría sobrepeso. */
val imcSobrepeso = Color(0xFFFFB300)
/** Color de la categoría obesidad. */
val imcObesidad = Color(0xFFE53935)

val Purple80 = Color(0xFFD0BCFF)
val PurpleGrey80 = Color(0xFFCCC2DC)
val Pink80 = Color(0xFFEFB8C8)

val Purple40 = Color(0xFF6650a4)
val PurpleGrey40 = Color(0xFF625b71)
val Pink40 = Color(0xFF7D5260)
