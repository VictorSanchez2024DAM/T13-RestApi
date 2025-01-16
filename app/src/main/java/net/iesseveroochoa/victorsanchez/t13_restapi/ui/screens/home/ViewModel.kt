package net.iesseveroochoa.victorsanchez.t13_restapi.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import net.iesseveroochoa.victorsanchez.t13_restapi.data.api.Repository

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
}