package com.example.imcapp.composables.modulo1_vistasprincipales

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.imcapp.R

@Preview
@Composable
fun ImageEjemplo3(modifier: Modifier = Modifier) {
    Image(painter = painterResource(R.drawable.gomu),
        contentDescription = null)
}