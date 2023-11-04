package com.example.iseven.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.iseven.data.model.Evenness
import com.example.iseven.data.repo.EvennessRepository
import com.example.iseven.data.repo.KnownNumbersRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.lang.NullPointerException
import javax.inject.Inject

@HiltViewModel
class CheckFragmentViewModel @Inject constructor(
    private val evennessRepo: EvennessRepository,
    private val knownNumbersRepository: KnownNumbersRepository
): ViewModel() {
    private val _uiState = MutableLiveData<Evenness>()
    val uiState = _uiState as LiveData<Evenness>

    fun check(n: Int){
        viewModelScope.launch {
            val evenness = try {
                knownNumbersRepository.check(n)
            }catch(e: NullPointerException){
                evennessRepo.isEven(n)
            }
            _uiState.value = evenness
            knownNumbersRepository.submit(evenness)
        }
    }
}