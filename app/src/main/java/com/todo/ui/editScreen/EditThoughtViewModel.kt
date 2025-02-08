package com.todo.ui.editScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.todo.ApplicationContainer
import com.todo.data.ThoughtsRepo
import com.todo.model.AddThoughts
import com.todo.model.ThoughtsEntity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.util.UUID


class EditThoughtViewModel(private val thoughtsRepo: ThoughtsRepo) : ViewModel(){
    private val _uiState = MutableStateFlow<AddThoughts>(AddThoughts())

    val uiState: StateFlow<AddThoughts> = _uiState.asStateFlow()


   fun updateState(content: String) {
        _uiState.update { updatedThought ->
            updatedThought.copy(content = content)
        }
    }

    suspend fun updateThought(uuid: UUID, time: String){
        thoughtsRepo.updateThought(
            ThoughtsEntity(
                uuid,
                uiState.value.content,
                time
            )
        )

    }

    companion object {
        val Factory  = viewModelFactory {
            initializer {
                val myRepository = (this[APPLICATION_KEY] as ApplicationContainer).container.repository
                EditThoughtViewModel(myRepository)
            }
        }
    }
}