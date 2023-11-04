package com.example.iseven.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.iseven.data.model.KnownListItem
import com.example.iseven.data.repo.KnownNumbersRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class KnownNumbersFragmentViewModel @Inject constructor(private val knownNumbersRepository: KnownNumbersRepository): ViewModel() {

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