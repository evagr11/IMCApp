package com.example.imcapp.core

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
data object Menu : NavKey

@Serializable
data object IMC : NavKey

// Ruta con argumento: le pasamos el IMC calculado a la pantalla de resultado
@Serializable
data class IMCResult(val result: Double) : NavKey
