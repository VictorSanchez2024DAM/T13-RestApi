package net.iesseveroochoa.victorsanchez.t13_restapi.ui.screens.favoritos

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import net.iesseveroochoa.victorsanchez.t13_restapi.ui.screens.home.HomeViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import net.iesseveroochoa.victorsanchez.t13_restapi.R
import net.iesseveroochoa.victorsanchez.t13_restapi.data.model.Personaje
import net.iesseveroochoa.victorsanchez.t13_restapi.ui.screens.home.PersonajeItem

/**
 * Pantalla de favoritos.
 */
@Composable
fun FavoritosScreen(
    onPersonajeClick: (Personaje) -> Unit,
    viewModel: HomeViewModel = viewModel(),
    modifier: Modifier = Modifier
) {
    // Obtenemos la lista de favoritos desde el ViewModel
    val favoritos by viewModel.favoritos.collectAsState(initial = emptyList())

    // Estado para manejar el diálogo
    var personajeAEliminar by remember { mutableStateOf<Personaje?>(null) }

    // Diseño principal
    Column(modifier = modifier.fillMaxSize()) {
        // Título
        Column(modifier = Modifier.fillMaxSize()) {
            Text(
                text = stringResource(R.string.favoritos),
                textAlign = TextAlign.Center,
                fontSize = 40.sp,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.fillMaxWidth()
            )
// Verificamos si hay favoritos
            if (favoritos.isEmpty()) {
                Text(
                    text = stringResource(R.string.no_hay_personajes_favoritos),
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            } else {
                LazyColumn {
                    items(favoritos) { personaje ->
                        PersonajeItem(
                            personaje = personaje,
                            onItemClick = { onPersonajeClick(personaje) },
                            isFavorito = true,
                            onFavoriteClick = { personajeAEliminar = personaje },
                            fromFavoritos = true
                        )
                    }
                }
            }
        }
    }

    // Diálogo de confirmación
    if (personajeAEliminar != null) {
        AlertDialog(
            onDismissRequest = { personajeAEliminar = null },
            title = { Text(text = stringResource(R.string.confirmacion_eliminar)) },
            text = {
                Text(
                    text = stringResource(
                        R.string.deseas_eliminar_favorito,
                        personajeAEliminar?.name ?: ""
                    )
                )
            },
            confirmButton = {
                TextButton(onClick = {
                    personajeAEliminar?.let { viewModel.removeFavorito(it) }
                    personajeAEliminar = null
                }) {
                    Text(text = stringResource(R.string.si))
                }
            },
            dismissButton = {
                TextButton(onClick = { personajeAEliminar = null }) {
                    Text(text = stringResource(R.string.no))
                }
            }
        )
    }
}


