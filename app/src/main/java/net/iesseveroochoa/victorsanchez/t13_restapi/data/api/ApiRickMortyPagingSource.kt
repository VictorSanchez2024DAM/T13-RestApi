package net.iesseveroochoa.victorsanchez.t13_restapi.data.api

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import net.iesseveroochoa.victorsanchez.t13_restapi.data.model.Personaje

/**
 * Clase `ApiRickMortyPagingSource` que extiende `PagingSource` para implementar la lógica de
 * paginación de personajes desde la API de Rick y Morty. *
 * Esta clase utiliza `PagingSource` para manejar la carga de datos paginados en una
aplicación
 * de Android, integrándose con el componente Paging de Jetpack.
 * Tenemos que indicar el tipo de la página (generalmente `Int`) y el tipo de datos que se
cargará
 * en nuestro caso Personaje.
 */
class ApiRickMortyPagingSource():
    PagingSource<Int, Personaje>()
{ /**
 * Método que carga los datos de una página específica desde la API de Rick y Morty.
 *
 * @param params Parámetros de carga que incluyen la clave (`key`) de la página actual.
 * @return Un objeto `LoadResult` que contiene los datos cargados, las claves de las
páginas previa
 * y siguiente, o un error si ocurrió algún problema.
 */
override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Personaje> {
    return try {
//si es null es que es la primera, en otro caso es la siguiente
        val nextPage = params.key ?: 1
//Le pedimos al servicio la siguiente página de personajes
        val siguientesPersonajesApi =
            NetworkService.servicioRickMorty.listaPersonajes(nextPage)
//obtenemos la lista de personajes según hemos definido el modelo
        val personajes = siguientesPersonajesApi.body()?.listaPersonajes
//observa en el logcat como carga las páginas
        Log.i("T11-REstApi", "Personajes cargados: ${personajes?.size}, página: $nextPage")
//devolvemos el resultado indicando:
        LoadResult.Page(
//resultado de la lista de personajes
            data = personajes!!,
//La clave de la página anterior. Si es la primera página, no hay página anterior.
            prevKey = if (nextPage == 1) null else nextPage - 1,
//La clave de la página siguiente. Si no hay más páginas, es null.
            nextKey = if (personajes!!.isEmpty()) null else nextPage + 1
        )
//Hemos tenido un error
    }catch (e:Exception){
        LoadResult.Error(e)
    }
}
    /**
     * Se llama a este método cuando la biblioteca de Paging necesita volver a cargar
    elementos para la
     * IU porque los datos en su PagingSource de copia de seguridad cambiaron.
     * Esta situación en la que los datos subyacentes de una PagingSource cambiaron y
    necesitan actualizarse en la IU
     * se denomina anulación
     *
     * @param state Estado actual de la paginación, que incluye información sobre la posición
    ancla.
     * @return La posición ancla (`anchorPosition`) que será usada para determinar la página a
    refrescar,
     * o `null` si no hay una posición definida.
     */
    override fun getRefreshKey(state: PagingState<Int, Personaje>): Int? {
//será la última posición en la que estabamos
        return state.anchorPosition
    }
}