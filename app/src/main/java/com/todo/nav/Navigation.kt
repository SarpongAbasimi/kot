package com.todo.nav

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.todo.ui.addScreen.AddThoughtScreen
import com.todo.ui.thoughtscreen.ThoughtScreen


sealed interface NavNames {
    fun name(): String
}

data object Home: NavNames {
    override fun name(): String {
        return "home"
    }
}

data object Create: NavNames {
    override fun name(): String {
        return "create"
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Navigation(modifier: Modifier = Modifier, controller: NavHostController){
    NavHost(navController = controller, startDestination = "home"){
        composable(Home.name()) {
            ThoughtScreen(modifier)
        }
        composable(Create.name()) {
            AddThoughtScreen(modifier)
        }
    }
}