package com.example.iseven.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.iseven.data.model.Evenness
import com.example.iseven.data.repo.EvennessRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ViewKnownNumberFragmentViewModel @Inject constructor(private val repo: EvennessRepository, private val savedStateHandle: SavedStateHandle): ViewModel() {

    private val _uiState = MutableLiveData<Evenness>()
    val uiState = _uiState as LiveData<Evenness>
    init {
        viewModelScope.launch { savedStateHandle.get<Int>("n")?.let { _uiState.postValue(repo.isEven(it)) } }
    }
}