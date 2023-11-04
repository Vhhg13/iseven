package com.example.iseven.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.iseven.App
import com.example.iseven.data.model.KnownListItem
import com.example.iseven.data.repo.KnownNumbersRepository
import kotlinx.coroutines.launch

class KnownNumbersFragmentViewModel(val knownNumbersRepository: KnownNumbersRepository): ViewModel() {
    companion object{
        val Factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory{
            override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
                val application = checkNotNull(extras[APPLICATION_KEY])
                return KnownNumbersFragmentViewModel((application as App).knownNumbersRepository) as T
            }
        }
    }

    private var _uiState = MutableLiveData<List<KnownListItem>>(listOf())
    val uiState = _uiState as LiveData<List<KnownListItem>>
    init {
        viewModelScope.launch {
            _uiState.value = knownNumbersRepository.getNumbers()
        }
    }

    fun remove(item: KnownListItem){
        viewModelScope.launch {
            knownNumbersRepository.remove(item.number)
            _uiState.value = knownNumbersRepository.getNumbers()
        }
    }
}