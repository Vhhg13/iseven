package com.example.iseven.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.iseven.data.model.Evenness
import com.example.iseven.data.repo.EvennessRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class KnownNumbersFragmentViewModel @Inject constructor(private val evennessRepository: EvennessRepository): ViewModel() {

    private var _uiState = MutableLiveData<List<Evenness>>(listOf())
    val uiState = _uiState as LiveData<List<Evenness>>
    init {
        viewModelScope.launch {
            _uiState.postValue(evennessRepository.getNumbers())
        }
    }

    fun remove(item: Evenness){
        viewModelScope.launch {
            evennessRepository.remove(item.number)
            _uiState.postValue(evennessRepository.getNumbers())
        }
    }
}