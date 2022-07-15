package com.rafaeltamayo.themeit.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rafaeltamayo.themeit.data.ThemeColors
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.lang.Exception

data class CreateThemeUIState(
    val colors : ThemeColors? = null,
    val errorMessage : String = ""
)

class CreateThemeViewModel() : ViewModel() {

    private val _uiState = MutableStateFlow(CreateThemeUIState())
    val uiState : StateFlow<CreateThemeUIState> = _uiState.asStateFlow()

    //TODO add data layer & fetch by id
    fun fetchTheme() {
        viewModelScope.launch {
            try {
                val colors = ThemeColors()
                _uiState.update {
                    it.copy(colors = colors)
                }
            } catch (e: Exception) {
                _uiState.update {
                    it.copy(errorMessage = "Error")
                }
            }
        }
    }
}