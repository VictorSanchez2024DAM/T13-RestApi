package net.iesseveroochoa.victorsanchez.t13_restapi.data.model

/**
 * Modelo de datos para los personajes de la API de Rick and Morty.
 */
data class Info(
    val count: Int,
    val next: String,
    val pages: Int,
    val prev: String
)