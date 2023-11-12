package com.example.iseven.ui.viewmodels

import android.graphics.Bitmap
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.iseven.data.repo.ImageRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.newSingleThreadContext
import javax.inject.Inject
@OptIn(DelicateCoroutinesApi::class)
@HiltViewModel
class ImageFragmentViewModel @Inject constructor(private val imageRepo: ImageRepository): ViewModel() {
    private val _uiState = MutableLiveData<Bitmap>()
    val uiState = _uiState as LiveData<Bitmap>

    private val _images = MutableLiveData<List<Bitmap>>()
    val images = _images as LiveData<List<Bitmap>>


    private val networkDispatcher = newSingleThreadContext("Network")
    private val diskDispatcher = newSingleThreadContext("Disk")

    init {
        viewModelScope.launch(diskDispatcher) {
            _images.postValue(imageRepo.getImages())
        }
    }

    fun load(url: String){
        viewModelScope.launch(networkDispatcher) {
            val bitmap = imageRepo.loadImage(url)
            if(bitmap != null)
                _uiState.postValue(bitmap!!)
        }
    }

    fun save(bitmap: Bitmap){
        viewModelScope.launch(diskDispatcher){
            _images.postValue(buildList {
                _images.value?.forEach { add(it) }
                add(bitmap)
            })
            imageRepo.saveImage(bitmap)
        }
    }

    fun clearImages(){
        viewModelScope.launch {
            imageRepo.removeImages()
            _images.postValue(imageRepo.getImages())
        }
    }
}