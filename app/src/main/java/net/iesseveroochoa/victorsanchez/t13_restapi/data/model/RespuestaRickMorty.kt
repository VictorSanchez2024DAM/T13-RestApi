package net.iesseveroochoa.victorsanchez.t13_restapi.data.model

import com.google.gson.annotations.SerializedName

data class RespuestaRickMorty(
    val info: Info,

    @SerializedName("results")
    val listaPersonajes: List<Personaje>
)