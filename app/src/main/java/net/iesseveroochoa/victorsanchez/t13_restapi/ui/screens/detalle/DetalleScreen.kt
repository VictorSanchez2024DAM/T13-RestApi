package net.iesseveroochoa.victorsanchez.t13_restapi.ui.screens.detalle

import androidx.compose.foundation.background
import coil3.compose.AsyncImage
import net.iesseveroochoa.victorsanchez.t13_restapi.data.model.Personaje


import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import net.iesseveroochoa.victorsanchez.t13_restapi.R

/**
 * Muestra los detalles de un personaje.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetalleScreen(
    personaje: Personaje,
    onBackClick: () -> Unit = {}
) {
    Scaffold(
        topBar = {
            // Barra de navegación con el botón de retroceso y el título
            TopAppBar(
                title = {
                    Text(text = stringResource(R.string.detalles_del_personaje))
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = stringResource(R.string.volver)
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(paddingValues)
        ) {

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(
                                MaterialTheme.colorScheme.primary,
                                MaterialTheme.colorScheme.primaryContainer
                            )
                        )
                    ),
                contentAlignment = Alignment.Center
            ) {
                // Imagen del personaje
                AsyncImage(
                    model = personaje.image,
                    contentDescription = "Imagen de ${personaje.name}",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                )
            }

            // Información del personaje
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = personaje.name,
                    style = MaterialTheme.typography.headlineMedium,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.onBackground
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Estado: ${personaje.status}",
                    style = MaterialTheme.typography.bodyMedium,
                    color = when (personaje.status) {
                        "Alive" -> Color(0xFF4CAF50) //
                        "Dead" -> Color(0xFFF44336) //
                        else -> MaterialTheme.colorScheme.onBackground //
                    }
                )
                Text(
                    text = "Especie: ${personaje.species}",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.secondary
                )
                Text(
                    text = "Género: ${personaje.gender}",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.secondary
                )
            }
        }
    }
}


