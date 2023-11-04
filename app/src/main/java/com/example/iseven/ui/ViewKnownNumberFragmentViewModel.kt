package com.example.iseven.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.iseven.App
import com.example.iseven.data.model.Evenness
import com.example.iseven.data.repo.EvennessRepository
import com.example.iseven.data.repo.KnownNumbersRepository
import kotlinx.coroutines.launch

class ViewKnownNumberFragmentViewModel(private val repo: KnownNumbersRepository): ViewModel() {
    companion object{
        val Factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory{
            override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
                val app = extras[APPLICATION_KEY] as App
                return ViewKnownNumberFragmentViewModel(app.knownNumbersRepository) as T
            }
        }
    }

    private val _uiState = MutableLiveData<Evenness>()
    val uiState = _uiState as LiveData<Evenness>
    fun check(n: Int){
        viewModelScope.launch {
            _uiState.value = repo.check(n)
        }
    }
}