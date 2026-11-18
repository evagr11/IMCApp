package com.example.imcapp.core

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

/** Ruta del menú principal ([com.example.imcapp.composables.features.imc.MenuScreen]). */
@Serializable
data object Menu : NavKey

/** Ruta de la calculadora ([com.example.imcapp.composables.features.imc.IMCScreen]). */
@Serializable
data object IMC : NavKey

// Ruta con argumento: le pasamos el IMC calculado a la pantalla de resultado
/**
 * Ruta de la pantalla de resultado.
 *
 * @property result IMC calculado en la calculadora, que se pasa a
 * [com.example.imcapp.composables.features.imc.IMCResultScreen].
 */
@Serializable
data class IMCResult(val result: Double) : NavKey
