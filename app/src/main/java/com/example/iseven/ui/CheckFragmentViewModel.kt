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
import java.lang.Exception
import java.lang.NullPointerException

class CheckFragmentViewModel(val evennessRepo: EvennessRepository, val knownNumbersRepository: KnownNumbersRepository): ViewModel() {
    companion object{
        val Factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory{
            override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
                val application = checkNotNull(extras[APPLICATION_KEY]) as App
                return CheckFragmentViewModel(application.evennessRepository, application.knownNumbersRepository) as T
            }
        }
    }
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