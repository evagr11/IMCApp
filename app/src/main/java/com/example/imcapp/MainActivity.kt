package com.example.imcapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.imcapp.core.AppNavigation
import com.example.imcapp.ui.theme.CursoAndroidComposeTheme

/**
 * Única actividad de IMCApp.
 *
 * Activa el modo de borde a borde, aplica el tema [CursoAndroidComposeTheme]
 * y carga [AppNavigation], que decide qué pantalla se muestra.
 */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CursoAndroidComposeTheme {
                AppNavigation()
                //MenuScreen(modifier = Modifier.fillMaxWidth())
                //IMCScreen()
                //Ejercicio2_TextField_StateHoisting()
            // CounterScreen_Example4(modifier = Modifier.fillMaxWidth())
            //counterScreen()
            //TextFieldExample_1()
               // TextEjemplo1()
                //ButtonEjemplo2()

//               Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
//                    Greeting(
//                        name = "Android",
//                        modifier = Modifier.padding(innerPadding)
//                    )
//                }
            }//fin CursoAndroid...
        }
    }
}

/**
 * Saludo de ejemplo que crea Android Studio con el proyecto. La app no lo usa.
 *
 * @param name nombre que se muestra en el saludo.
 * @param modifier modificador que se aplica al texto.
 */
@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

/** Vista previa de [Greeting] en Android Studio. */
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CursoAndroidComposeTheme {
        var name="Encarna"
        Greeting(name)
    }
}