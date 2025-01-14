package net.iesseveroochoa.victorsanchez.t13_restapi.ui.components

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.navigation.NavController

/**
 * Composable que representa la barra de navegación inferior.
 * @param items Lista de elementos de navegación, cada uno define una ruta y sus íconos asociados.
 * @param navController Controlador de navegación para gestionar el flujo entre las pantallas.
 */
@Composable
fun BarraNavegacion(
    items: List<NavigationItem>,
    navController: NavController
) {
// Estado que rastrea el índice de la pestaña seleccionada actualmente. `rememberSaveable` asegura que el estado persista en recreaciones de la composición.
    var selectedTabIndex by rememberSaveable {
        mutableStateOf(0)//pantalla inicial
    }
// Contenedor de la barra de navegación.
    NavigationBar {
// Recorre los elementos de navegación junto con su índice.
        items.forEachIndexed { index, item ->
// Comprueba si la pestaña actual está seleccionada.
            val isSelected = selectedTabIndex == index
// Define un ítem individual en la barra de navegación.
            NavigationBarItem(
                selected = isSelected, // Indica si el ítem está seleccionado.
                onClick = {
// Actualiza el índice seleccionado y navega a la ruta correspondiente.
                    selectedTabIndex = index
                    navController.navigate(item.route) {
// Configura las opciones de navegación:
                        popUpTo(navController.graph.startDestinationId) { saveState = true } // Mantiene el estado al regresar.
                        launchSingleTop = true // Evita crear múltiples instancias de la misma ruta.
                        restoreState = true // Restaura el estado guardado de la pestaña.
                    }
                },
                icon = {
// Muestra el ícono dependiendo del estado seleccionado.
                    Icon(
                        imageVector = if (isSelected) item.selectedIcon else item.unselectedIcon,
                        contentDescription = item.label // Descripción para accesibilidad.
                    )
                },
                label = {
// Muestra el texto de la pestaña, con formato dependiendo del estado seleccionado.
                    Text(
                        text = item.label,
                        textDecoration = if (isSelected) TextDecoration.Underline else TextDecoration.None,
                        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
                    )
                }
            )
        }
    }
}
//
/**
 * Modelo de datos que representa un ítem de navegación.
 * @param route Ruta o destino al que navega este ítem.
 * @param label Texto que se muestra en la pestaña.
 * @param selectedIcon Ícono que se muestra cuando está seleccionado.
 * @param unselectedIcon Ícono que se muestra cuando no está seleccionado.
 */
data class NavigationItem(
    val route: Any, // Ruta o destino al que navega este ítem.
    val label: String, // Texto que se muestra en la pestaña.
    val selectedIcon: ImageVector, // Ícono que se muestra cuando está seleccionado.
    val unselectedIcon: ImageVector // Ícono que se muestra cuando no está seleccionado.
)