package net.iesseveroochoa.victorsanchez.t13_restapi

import android.app.Application
import net.iesseveroochoa.victorsanchez.t13_restapi.data.repository.Repository

/**
 * Aplicación personalizada que inicializa el repositorio.
 */
class PersonajesApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Repository(this)
    }
}