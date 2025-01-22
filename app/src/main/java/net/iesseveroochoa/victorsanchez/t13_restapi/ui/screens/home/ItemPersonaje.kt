package net.iesseveroochoa.victorsanchez.t13_restapi.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import net.iesseveroochoa.victorsanchez.t13_restapi.R
import net.iesseveroochoa.victorsanchez.t13_restapi.data.db.PersonajeEntity
import net.iesseveroochoa.victorsanchez.t13_restapi.data.model.Personaje

/**
 * Composable que muestra un elemento de la lista de personajes.
 */
@Composable
fun PersonajeItem(
    personaje: Personaje,
    onItemClick: () -> Unit={},
    onFavoriteClick: (Personaje) -> Unit,
    isFavorito: Boolean,
    fromFavoritos: Boolean
){
    // Contenedor del diseño del Item
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .clickable { onItemClick() },
        shape = RoundedCornerShape(8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.LightGray)
                .padding(8.dp)
                .background(Color(0xFFF1F8E9))
        ) {
            // Imagen del personaje
            AsyncImage(
                model = personaje.image,
                contentDescription = "Imagen de ${personaje.name}",
                placeholder = painterResource(R.drawable.placeholder),
                error = painterResource(R.drawable.error),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(80.dp)
                    .clip(RoundedCornerShape(8.dp))
            )

            Spacer(modifier = Modifier.width(8.dp))

            // Información del personaje
            Column(
                modifier = Modifier.align(Alignment.CenterVertically)
            ) {
                Text(
                    text = personaje.name,
                    style = MaterialTheme.typography.titleSmall,
                    color = Color.Black
                )
                Text(
                    text = personaje.especie,
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Gray
                )
                Text(
                    text = personaje.estado,
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Green
                )


            }
            // Botón de favorito
            IconButton(onClick = { onFavoriteClick(personaje) }) {
                Icon(
                    imageVector = if(fromFavoritos) Icons.Default.Close else
                    if (isFavorito) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                    contentDescription = if (isFavorito) stringResource(R.string.quitar_de_favoritos) else stringResource(
                        R.string.a_adir_a_favoritos
                    )
                )
            }

        }
    }
}