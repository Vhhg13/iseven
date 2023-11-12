package com.example.iseven.ui.composables

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.LifecycleOwner
import com.example.iseven.ui.viewmodels.ViewKnownNumberFragmentViewModel

@Composable
fun SeeNumberScreen(
    viewModel: ViewKnownNumberFragmentViewModel = hiltViewModel(),
    lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current
) {
    var text by remember {
        mutableStateOf("")
    }
    LaunchedEffect(key1 = true){
        viewModel.uiState.observe(lifecycleOwner){
            text = it.number.toString()
        }
    }
    Text(text = text)
}