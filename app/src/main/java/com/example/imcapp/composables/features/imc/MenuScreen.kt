package com.example.imcapp.composables.features.imc

import android.widget.Toast
import androidx.compose.material3.Button
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

/**
 * Menú principal del proyecto de clase.
 *
 * El botón «IMC Calculator» abre la calculadora y «TODO» muestra un aviso
 * («Próximamente») reservado para las siguientes apps del curso.
 *
 * @param modifier modificador del [Scaffold] y de la columna de botones.
 * @param onIMCSelected se llama al pulsar «IMC Calculator».
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuScreen(modifier: Modifier = Modifier, onIMCSelected:() -> Unit) {
    val context = LocalContext.current

    Scaffold(modifier = modifier, topBar = {
        TopAppBar(title = {Text("Programación Multimedia y Dispositivos móviles App")} )
    }) {
        paddingValues ->
        Column(modifier = modifier.padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally) {
            Button(onClick = {
                onIMCSelected()
            }) {
                Text("IMC Calculator")
            }
            Spacer(modifier = Modifier.height(24.dp))
            Button(onClick = {
                Toast.makeText(context, "Próximamente", Toast.LENGTH_SHORT).show()
            }) {
                Text("TODO")
            }
        }
    }
}