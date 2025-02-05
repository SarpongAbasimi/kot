package com.todo

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.todo.ui.theme.TodoTheme
import com.todo.ui.thoughtscreen.ThoughtScreen


class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
            setContent {
                TodoTheme {
                    Scaffold(
                        topBar = {
                           TopAppBar(
                               title = {
                                   Text(
                                       "Thoughts",
                                       textAlign = TextAlign.Center,
                                       modifier = Modifier.fillMaxWidth()
                                   )
                                       },
                               colors = topAppBarColors(
                                   containerColor = MaterialTheme.colorScheme.surfaceContainerHighest,
                                   titleContentColor = MaterialTheme.colorScheme.onBackground,
                               ),
                               modifier = Modifier
                           )
                        },
                        bottomBar = {
                            BottomAppBar(
                                containerColor = MaterialTheme.colorScheme.surfaceContainerHighest,
                                contentColor = MaterialTheme.colorScheme.onBackground,
                                actions = {
                                    IconButton(onClick = {}) {
                                        Icon(
                                            Icons.Filled.Home,
                                            contentDescription = "Localized description",
                                            Modifier.size(40.dp)
                                        )
                                    }
                                },
                                floatingActionButton = {
                                    FloatingActionButton(
                                        onClick = {  },
                                    ) {
                                        Icon(
                                            Icons.Filled.Add,
                                            "Floating action button."
                                        )
                                    }
                                }
                            )
                        },
                        modifier = Modifier.fillMaxSize()
                    ) { innerPadding ->
                        ThoughtScreen(Modifier.padding(innerPadding))
                    }
                }
            }

    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Preview(showBackground = true)
    @Composable
    fun AppPreview(modifier: Modifier = Modifier){
            TodoTheme {
                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = {
                                Text(
                                    "Thoughts",
                                    textAlign = TextAlign.Center,
                                    modifier = Modifier.fillMaxWidth()
                                )
                            },
                            colors = topAppBarColors(
                                containerColor = MaterialTheme.colorScheme.surfaceContainerHighest,
                                titleContentColor = MaterialTheme.colorScheme.onBackground,
                            ),
                            modifier = Modifier
                        )
                    },
                    bottomBar = {
                        BottomAppBar(
                            containerColor = MaterialTheme.colorScheme.surfaceContainerHighest,
                            contentColor = MaterialTheme.colorScheme.onBackground,
                            actions = {
                                Row(
                                    horizontalArrangement = Arrangement.Center,
                                    modifier = Modifier.fillMaxWidth()
                                ) {
                                    IconButton(onClick = {}) {
                                        Icon(
                                            Icons.Filled.Home,
                                            contentDescription = "Localized description",
                                            Modifier.size(40.dp)
                                        )
                                    }

                                    Spacer(Modifier.size(10.dp))

                                    IconButton(onClick = {}) {
                                        Icon(
                                            Icons.Filled.Edit,
                                            contentDescription = "Localized description",
                                            Modifier.size(40.dp)
                                        )
                                    }
                                }
                            },
                            floatingActionButton = {
                                FloatingActionButton(
                                    onClick = { /* do something */ },
                                    containerColor = BottomAppBarDefaults.bottomAppBarFabColor,
                                    elevation = FloatingActionButtonDefaults.bottomAppBarFabElevation()
                                ) {
                                    Icon(Icons.Filled.Add, "Localized description")
                                }
                            }
                        )
                    },
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    Text(
                        modifier = Modifier.padding(innerPadding),
                        text = "Example of a scaffold with a bottom app bar."
                    )
                }
            }

    }
}