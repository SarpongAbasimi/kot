package com.todo

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.todo.ui.theme.TodoTheme
import com.todo.ui.thoughtscreen.ThoughtScreen


class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
            setContent {
                TodoTheme {
                    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                        ThoughtScreen(Modifier.padding(innerPadding))
                    }
                }
            }

    }
}