package com.todo.ui.thoughtscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope

import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.todo.ApplicationContainer
import com.todo.data.ThoughtsRepo
import com.todo.model.ThoughtsEntity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


class ThoughtsViewModel(private val thoughtsRepo: ThoughtsRepo): ViewModel() {


    private val _uiState = MutableStateFlow<List<ThoughtsEntity>>(listOf<ThoughtsEntity>())

    val uiState: StateFlow<List<ThoughtsEntity>> = _uiState.asStateFlow()

    init {
        viewModelScope.launch{
            getThoughts()
        }
    }

    private suspend fun getThoughts(): Unit {
        return try {
            thoughtsRepo.getThoughts().collect { thoughts ->
                _uiState.update {
                    thoughts
                }
            }
        } catch (error: Exception) {
            println("Error whiles getting thoughts")
        }
    }


    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val myRepository = (this[APPLICATION_KEY] as ApplicationContainer).container.repository
                ThoughtsViewModel(myRepository)
            }
        }
    }
}