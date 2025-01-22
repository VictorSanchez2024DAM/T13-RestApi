package net.iesseveroochoa.victorsanchez.t13_restapi.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import net.iesseveroochoa.victorsanchez.t13_restapi.data.db.PersonajeEntity
import net.iesseveroochoa.victorsanchez.t13_restapi.data.model.Personaje
import net.iesseveroochoa.victorsanchez.t13_restapi.data.repository.Repository

/**
 * ViewModel para la pantalla de inicio y Favoritos.
 */
class HomeViewModel : ViewModel() {
//-------------Paginación----------------
//los items por página. Creo que no tiene efecto en nuestro caso, ya que el servicio trae páginas de 20 en 20
    val MAX_ITEMS = 10

    //cuando carga mas items. Si haces scroll y te quedan 3 items, carga más
    val PREFETCH_DISTANCE = 3
    val personajes = Pager(
        config = PagingConfig(
            pageSize = MAX_ITEMS,
            prefetchDistance = PREFETCH_DISTANCE,
            enablePlaceholders = false
        ),
        pagingSourceFactory = {
            Repository.getPersonajesApiPagingSource()
        }
    )//devolvemos una flow de la paginación
        .flow
//para mantener el estado de paginación a través de los cambios de configuración o navegación
    .cachedIn(viewModelScope)


    // Lista de favoritos
    private val _favoritos = MutableStateFlow<List<Personaje>>(emptyList())
    val favoritos: StateFlow<List<Personaje>> = _favoritos

    init {
        // Cargar favoritos iniciales desde la base de datos
        viewModelScope.launch {
            Repository.getAllPersonajes().collect { personajesEntity ->
                _favoritos.value = personajesEntity.map { it.toPersonaje() }
            }
        }
    }

    // Funciones para gestionar favoritos
    fun addFavorito(personaje: Personaje) {
        viewModelScope.launch {
            val personajeEntity = personaje.toEntity()
            Repository.addPersonaje(personajeEntity)
        }
    }

    fun removeFavorito(personaje: Personaje) {
        viewModelScope.launch {
            val personajeEntity = personaje.toEntity()
            Repository.removePersonaje(personajeEntity)
        }
    }

    // Funciones para convertir entre Personaje y PersonajeEntity
    fun Personaje.toEntity(): PersonajeEntity {
        return PersonajeEntity(
            id = this.id,
            name = this.name,
            status = this.status,
            species = this.species,
            gender = this.gender,
            location = "",
            image = this.image,
            created = this.created
        )
    }

    fun PersonajeEntity.toPersonaje(): Personaje {
        return Personaje(
            id = this.id,
            name = this.name,
            status = this.status,
            species = this.species,
            type = "",
            gender = this.gender,
            image = this.image,
            created = this.created
        )
    }
}