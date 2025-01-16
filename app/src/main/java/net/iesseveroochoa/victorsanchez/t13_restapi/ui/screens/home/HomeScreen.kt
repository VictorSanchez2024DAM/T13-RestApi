package net.iesseveroochoa.victorsanchez.t13_restapi.ui.screens.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import net.iesseveroochoa.victorsanchez.t13_restapi.data.api.NetworkService
import net.iesseveroochoa.victorsanchez.t13_restapi.data.model.RespuestaRickMorty

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier
){
    var respuesta by remember { mutableStateOf<RespuestaRickMorty?>(null) }
    LaunchedEffect(true) {
// Coroutine code that will be relaunched when count changes
        respuesta = NetworkService.servicioRickMorty.listaPersonajes().body()
    }

    Column(modifier = Modifier.fillMaxSize()){
        Text(
            text = "Rick y Morty",
            textAlign = TextAlign.Center,
            fontSize = 40.sp,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.fillMaxSize()
        )

    }

}