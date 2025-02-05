package com.todo.ui.addScreen

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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.todo.ui.theme.TodoTheme
import java.util.UUID


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AddThoughtScreen(
    modifier: Modifier = Modifier,
    addThoughtsViewModel: AddThoughtsViewModel = viewModel(factory = AddThoughtsViewModel.Factory)
){
    val stateFlow = addThoughtsViewModel.uiState
    val uiState = stateFlow.collectAsState()

    Fields(
        modifier,
        { userInput -> addThoughtsViewModel.updateState(userInput)},
        uiState.value.content,
        {id ->
            addThoughtsViewModel.create(id)
            addThoughtsViewModel.updateState("")
        }
    )
}

@Composable
fun Fields(
     modifier: Modifier = Modifier,
     handleValueChange: (value: String) -> Unit,
     fieldValue: String,
     handleButtonClick: (UUID) -> Unit = {},
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
            onClick = { handleButtonClick(UUID.randomUUID()) },
            enabled = fieldValue.isNotEmpty(),
            colors = ButtonColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                contentColor = MaterialTheme.colorScheme.onPrimary,
                disabledContainerColor = MaterialTheme.colorScheme.surfaceVariant,
                disabledContentColor = MaterialTheme.colorScheme.onSurfaceVariant 
            )
        ) {
            Text("Create")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FieldsPreview(
    modifier: Modifier = Modifier,
    viewModel: AddThoughtsViewModel = viewModel(factory = AddThoughtsViewModel.Factory)
){
    TodoTheme {
        Fields(
            modifier,
            { state -> viewModel.updateState(state)} ,
            viewModel.uiState.collectAsState().value.content
        )
    }
}