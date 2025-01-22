package net.iesseveroochoa.victorsanchez.t13_restapi.data.db

import androidx.room.*
import kotlinx.coroutines.flow.Flow

/**
 * Interfaz para acceder a la base de datos.
 */
@Dao
interface PersonajeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(personaje: PersonajeEntity)

    @Delete
    suspend fun delete(personaje: PersonajeEntity)

    @Query("SELECT * FROM personajes")
    fun getAll(): Flow<List<PersonajeEntity>>


}
