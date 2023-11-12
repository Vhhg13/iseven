package com.example.iseven.util

sealed interface UiEvent {
    data object PopBackStack: UiEvent
    data class Navigate(val route: String): UiEvent
}