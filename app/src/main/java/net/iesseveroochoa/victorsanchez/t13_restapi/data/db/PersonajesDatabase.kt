package net.iesseveroochoa.victorsanchez.t13_restapi.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import net.iesseveroochoa.victorsanchez.t13_restapi.R

/**
 * Base de datos de la aplicación.
 */
@Database(entities = arrayOf(PersonajeEntity::class), version = 1, exportSchema = false)
@TypeConverters(TransformaFechaSQLite::class) // Registramos los convertidores
abstract class PersonajesDatabase : RoomDatabase() {
    abstract fun personajeDao(): PersonajeDao
    companion object {
        @Volatile

        private var INSTANCE: PersonajesDatabase? = null
        fun getDatabase(context: Context): PersonajesDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PersonajesDatabase::class.java,
                    context.getString(R.string.personajes_database)
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
