package net.iesseveroochoa.victorsanchez.t13_restapi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import net.iesseveroochoa.victorsanchez.t13_restapi.ui.navigation.AppNavigation
import net.iesseveroochoa.victorsanchez.t13_restapi.ui.theme.T13RestApiTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            T13RestApiTheme {
               AppNavigation()
            }
        }
    }
}

