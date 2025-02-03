package com.todo.ui.addScreen

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class AddThoughtsViewModel: ViewModel() {
    private val _uiState =  MutableStateFlow<String>("")
    val uiState =_uiState.asStateFlow()

    fun updateState(userInput: String): Unit {
       return _uiState.update {
            userInput
        }
    }
}