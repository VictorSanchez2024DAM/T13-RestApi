package net.iesseveroochoa.victorsanchez.t13_restapi.data.model

import com.google.gson.annotations.SerializedName

/**
 * Modelo de datos para la respuesta de la API de Rick and Morty.
 */
data class RespuestaRickMorty(
    val info: Info,

    @SerializedName("results")
    val listaPersonajes: List<Personaje>
)