package com.example.iseven.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.iseven.data.model.Evenness
import com.example.iseven.data.repo.KnownNumbersRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ViewKnownNumberFragmentViewModel @Inject constructor(private val repo: KnownNumbersRepository): ViewModel() {

    private val _uiState = MutableLiveData<Evenness>()
    val uiState = _uiState as LiveData<Evenness>
    fun check(n: Int) =
        viewModelScope.launch {
            _uiState.value = repo.check(n)
        }
}