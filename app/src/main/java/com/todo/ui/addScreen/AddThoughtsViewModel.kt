package com.todo.ui.addScreen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.todo.ApplicationContainer
import com.todo.data.ThoughtsRepo
import com.todo.model.AddThoughts
import com.todo.model.ThoughtsEntity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.OffsetDateTime
import java.util.UUID


class AddThoughtsViewModel(private val thoughtsRepo: ThoughtsRepo): ViewModel() {
    private val _uiState =  MutableStateFlow<AddThoughts>(AddThoughts())
    val uiState =_uiState.asStateFlow()

    fun updateState(userInput: String): Unit {
       return _uiState.update { state ->
           state.copy(content = userInput)
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun create(id: UUID){
        viewModelScope.launch {
            thoughtsRepo.insertThoughts(
                ThoughtsEntity(
                    id,
                     uiState.value.content,
                     "${OffsetDateTime.now()}"
                    )
            )
        }
    }


    companion object {
        val Factory : ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val myRepository = (this[APPLICATION_KEY] as ApplicationContainer).container.repository
                AddThoughtsViewModel(myRepository)
            }
        }
    }
}