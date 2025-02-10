package com.todo.ui.editScreen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.OffsetDateTime
import java.util.UUID



@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun EditThoughtScreen(
    modifier: Modifier = Modifier,
    editThoughtsViewModel: EditThoughtViewModel = viewModel(factory = EditThoughtViewModel.Factory),
    thoughtId: UUID
){
    val addState = editThoughtsViewModel.uiState.collectAsState()
    val uiState = addState.value

    Fields(
        modifier,
        { userInput -> editThoughtsViewModel.updateState(userInput)},
        uiState.content,
        {
            CoroutineScope(Dispatchers.IO).launch {
                editThoughtsViewModel.updateThought(thoughtId, OffsetDateTime.now().toString())
                editThoughtsViewModel.updateState("")
            }
        }
    )
}


@Composable
fun Fields(
    modifier: Modifier = Modifier,
    handleValueChange: (value: String) -> Unit,
    fieldValue: String,
    handleButtonClick: () -> Unit = {},
){
    Column(
        modifier.fillMaxSize().background(MaterialTheme.colorScheme.scrim),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        TextField(
            value = fieldValue,
            onValueChange = { handleValueChange(it) },
            label = { Text("What is on your mind? 💭") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        )
        Spacer(Modifier.size(10.dp))
        FilledTonalButton(
            onClick = { handleButtonClick() },
            enabled = fieldValue.isNotEmpty(),
            colors = ButtonColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                contentColor = MaterialTheme.colorScheme.onPrimary,
                disabledContainerColor = MaterialTheme.colorScheme.surfaceVariant,
                disabledContentColor = MaterialTheme.colorScheme.onSurfaceVariant
            )
        ) {
            Text("Update")
        }
    }
}