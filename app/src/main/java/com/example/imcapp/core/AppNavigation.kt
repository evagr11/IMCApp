package com.example.imcapp.core

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.example.imcapp.composables.features.imc.IMCResultScreen
import com.example.imcapp.composables.features.imc.IMCScreen
import com.example.imcapp.composables.features.imc.MenuScreen

@Composable
fun AppNavigation() {
    val backStack = rememberNavBackStack(Menu)
    NavDisplay(
        backStack = backStack,
        onBack = { backStack.removeLastOrNull() },
        entryProvider = entryProvider {
            entry<Menu> {
                MenuScreen(
                    modifier = Modifier.fillMaxWidth(),
                    onIMCSelected = { backStack.add(IMC) }
                )
            }

            entry<IMC> {
                IMCScreen(
                    onCalculate = { imc -> backStack.add(IMCResult(imc)) },
                    onBack = { backStack.removeLastOrNull() }
                )
            }

            entry<IMCResult> { key ->
                IMCResultScreen(
                    result = key.result,
                    onRecalculate = { backStack.removeLastOrNull() }
                )
            }
        }
    )
}
