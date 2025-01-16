package net.iesseveroochoa.victorsanchez.t13_restapi.data.model

import java.text.DateFormat
import java.util.Date
import java.util.Locale

data class Personaje(
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val type: String,
    val gender: String,
    val image: String,
    val created: Date
    // val url: String
    // val origin: Origin,
    // val episode: List<String>,
    // val location: Location,
){
    val estado
        get()=when(status.lowercase()){
            "alive"->if(gender.lowercase()=="female") "Viva" else "Vivo"
            "dead"->if(gender.lowercase()=="female") "Muerta" else "Muerto"
            "unknown"->if(gender.lowercase()=="female") "Viva o Muerta" else "Vivo o Muerto"
            else -> status
        }
    val especie
        get() = when(species.lowercase()) {
            "human" -> if(gender.lowercase()=="female") "Humana" else "Humano"
            "alien" -> "Alienigena"
            "unknown" -> "Especie desconocida"
            else -> species
        }
    val genero
        get() = when(gender.lowercase()){
            "male"->"Masculino"
            "female"->"Femenino"
            else -> "LGTBI+"
        }
    val fecha:String
        get(){
            return DateFormat
                .getDateInstance(DateFormat.MEDIUM, Locale.getDefault())
                .format(created)
        }
}