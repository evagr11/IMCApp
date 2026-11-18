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

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CursoAndroidComposeTheme {
        var name="Encarna"
        Greeting(name)
    }
}