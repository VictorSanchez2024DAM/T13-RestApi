package net.iesseveroochoa.victorsanchez.t13_restapi.data.api

import net.iesseveroochoa.victorsanchez.t13_restapi.data.model.RespuestaRickMorty
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface RickMortyService {

    //Llamada con parámetros al endpoint "character"
    //En nuestro caso tenemos un parámetro que es el número de la página
    //Esta es la opción que utilizaremos
    @GET("character")
    suspend fun listaPersonajes(
        @Query("page") page:Int
    ): Response<RespuestaRickMorty>
    //Llamada al servicio sin parámetros
    @GET("character")
    suspend fun listaPersonajes():Response<RespuestaRickMorty>
    //Llamada al servicio directamente con la uri
    @GET
    suspend fun listaPersonajes(
        @Url
        siguientes:String):Response<RespuestaRickMorty>
}