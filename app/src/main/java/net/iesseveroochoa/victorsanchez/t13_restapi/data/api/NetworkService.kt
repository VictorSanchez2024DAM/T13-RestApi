package net.iesseveroochoa.victorsanchez.t13_restapi.data.api

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
* Esta clase nos permitirá soliciar nuevas peticiones a la API según las funciones
* definidad en la interface RickMortyService
*/
object NetworkService {
    //Base de la url de la Api
//IMPORTANTE: la baseURL siempre tiene que terminar con una barra horizontal
    private val URI_BASE = "https://rickandmortyapi.com/api/"
    //creamos el conversor de String a fecha de JSON. Lo hará automático
    private val gson = GsonBuilder()
        .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
        .create()
    //iniciamos retrofit con los métodos de acceso
    val servicioRickMorty = Retrofit.Builder()
        .baseUrl(URI_BASE)
    //conversor de JSON, existen otros(XML,csv...consultar ayuda)
        .addConverterFactory(GsonConverterFactory.create(gson))
    //Crea el servicio con los métodos definidos de la interface
        .build().create(RickMortyService::class.java)
}