package net.iesseveroochoa.victorsanchez.t13_restapi.ui.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.google.gson.Gson
import net.iesseveroochoa.victorsanchez.t13_restapi.data.model.Personaje
import net.iesseveroochoa.victorsanchez.t13_restapi.ui.components.BarraNavegacion
import net.iesseveroochoa.victorsanchez.t13_restapi.ui.components.NavigationItem
import net.iesseveroochoa.victorsanchez.t13_restapi.ui.screens.detalle.DetalleScreen
import net.iesseveroochoa.victorsanchez.t13_restapi.ui.screens.favoritos.FavoritosScreen
import net.iesseveroochoa.victorsanchez.t13_restapi.ui.screens.home.HomeScreen

@Composable
fun AppNavigation(){
    val navController = rememberNavController()

    fun personajeToJson(personaje: Personaje): String {
        val gson = Gson()
        return gson.toJson(personaje)
    }

    fun jsonToPersonaje(json: String): Personaje {
        val gson = Gson()
        return gson.fromJson(json, Personaje::class.java)
    }

    /**
     * Lista de objetos NavigationItem que definen los iconos de la barra y los destinos de
    la aplicación.
     */
    fun listaDestinos() = listOf(
        NavigationItem(//pantalla principal
            HomeDestination,
            "Inicio",
            Icons.Filled.Home,
            Icons.Outlined.Home
        ),
        NavigationItem(//favoritos
            FavoritosDestination,
            "Favoritos",
            Icons.Filled.Favorite,
            Icons.Outlined.FavoriteBorder
        )
    )

    Scaffold(
        bottomBar = {
            BarraNavegacion(
                items = listaDestinos(),
                navController = navController
            )
        }
    ) { padding ->
        NavHost(
            navController = navController,
            startDestination = HomeDestination,
            modifier = Modifier.padding(padding)
        ) {//Pantalla principal
            composable<HomeDestination>{
                HomeScreen(
                    onPersonajeClick = { personaje ->
                        val personajeJson = personajeToJson(personaje)
                        navController.navigate(DetallesDestination(personajeJson))
                    }
                )
            } // Pantalla favoritos
            composable<FavoritosDestination>{
                FavoritosScreen(

                )
            }
            // Pantalla de detalles
            composable<DetallesDestination>  { backStackEntry ->
                val detallesDestination: DetallesDestination = backStackEntry.toRoute()
                val personaje = jsonToPersonaje(detallesDestination.personaje)
                DetalleScreen(
                    personaje = personaje,
                    onBackClick = { navController.popBackStack() }
                )
            }
        }
    }
}