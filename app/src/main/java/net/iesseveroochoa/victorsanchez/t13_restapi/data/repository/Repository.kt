package net.iesseveroochoa.victorsanchez.t13_restapi.data.repository

import android.content.Context
import kotlinx.coroutines.flow.Flow
import net.iesseveroochoa.victorsanchez.t13_restapi.data.api.ApiRickMortyPagingSource
import net.iesseveroochoa.victorsanchez.t13_restapi.data.db.PersonajeDao
import net.iesseveroochoa.victorsanchez.t13_restapi.data.db.PersonajeEntity
import net.iesseveroochoa.victorsanchez.t13_restapi.data.db.PersonajesDatabase

/**
 * Repositorio de datos.
 */
object Repository {
    fun getPersonajesApiPagingSource() = ApiRickMortyPagingSource()
    // Modelo de datos
    private lateinit var personajeDao: PersonajeDao

    // Inicialización del objeto singleton
    operator fun invoke(context: Context) {
        personajeDao = PersonajesDatabase
            .getDatabase(context)
            .personajeDao()
    }

    // Métodos CRUD de la base de datos
    suspend fun addPersonaje(personaje: PersonajeEntity) = personajeDao.insert(personaje)

    suspend fun removePersonaje(personaje: PersonajeEntity) = personajeDao.delete(personaje)

    fun getAllPersonajes(): Flow<List<PersonajeEntity>> = personajeDao.getAll()


}
