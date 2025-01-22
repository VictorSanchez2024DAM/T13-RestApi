package net.iesseveroochoa.victorsanchez.t13_restapi.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.text.DateFormat
import java.util.Date
import java.util.Locale

/**
 * Entidad que representa a un personaje de Rick and Morty.
 */
@Entity(tableName = "personajes")
data class PersonajeEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val gender: String,
    val location: String,
    val image: String,
    val created: Date
)
