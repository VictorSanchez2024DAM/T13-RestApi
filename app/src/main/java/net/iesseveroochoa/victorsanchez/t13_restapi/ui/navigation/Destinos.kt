package net.iesseveroochoa.victorsanchez.t13_restapi.ui.navigation

import kotlinx.serialization.Serializable

@Serializable
object HomeDestination

@Serializable
object FavoritosDestination

@Serializable
data class DetallesDestination(val personaje: String)