package com.example.iseven.ui.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.Button
import androidx.compose.material.TextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.LifecycleOwner
import coil.compose.AsyncImage
import com.example.iseven.R
import com.example.iseven.ui.viewmodels.ImageFragmentViewModel

@Composable
fun ImageScreen(
    viewModel: ImageFragmentViewModel = hiltViewModel(),
    lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current
) {
    LaunchedEffect(key1 = true){
        viewModel.uiState.observe(lifecycleOwner){

        }
        viewModel.images.observe(lifecycleOwner){

        }
    }
    LazyRow{

    }

    Column {
        var link by rememberSaveable {
            mutableStateOf("")
        }
        TextField(value = link, onValueChange = { link = it })

        var image by rememberSaveable {
            mutableStateOf("")
        }
        Button(onClick = { image = link }) {
            Text(stringResource(id = R.string.check_evenness))
        }

        AsyncImage(model = image, contentDescription = null)
    }

}