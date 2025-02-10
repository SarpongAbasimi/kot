package com.todo.nav

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.todo.ui.addScreen.AddThoughtScreen
import com.todo.ui.editScreen.EditThoughtScreen
import com.todo.ui.thoughtscreen.ThoughtScreen
import java.util.UUID


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

data object Edit: NavNames {
    override fun name(): String {
        return "edit"
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Navigation(modifier: Modifier = Modifier, controller: NavHostController){
    NavHost(navController = controller, startDestination = "home"){
        composable(Home.name()) {
            ThoughtScreen(modifier, navigateEdit = {
                controller.navigate("${Edit.name()}/$it")
            })
        }

        composable(Create.name()) {
            AddThoughtScreen(modifier)
        }

        composable(
            "${Edit.name()}/{key}",
            arguments = listOf(navArgument("key", builder = { NavType.StringType }))
        ){ backStackEntry: NavBackStackEntry ->
            val thoughtsId = backStackEntry.arguments?.getString("key")

            if(thoughtsId.isNullOrEmpty()) {
                Text("Sorry, resource can't be found")
            } else {
                EditThoughtScreen(
                    modifier,
                    thoughtId = UUID.fromString(thoughtsId)
                )
            }
        }
    }
}