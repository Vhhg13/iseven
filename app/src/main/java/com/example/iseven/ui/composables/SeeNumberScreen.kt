package com.example.iseven.ui.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.LifecycleOwner
import com.example.iseven.R
import com.example.iseven.ui.viewmodels.ViewKnownNumberFragmentViewModel

@Composable
fun SeeNumberScreen(
    viewModel: ViewKnownNumberFragmentViewModel = hiltViewModel(),
    lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current
) {
    var text by rememberSaveable {
        mutableStateOf("")
    }
    var ad by remember {
        mutableStateOf("")
    }
    var even by rememberSaveable {
        mutableStateOf(false)
    }
    LaunchedEffect(key1 = true){
        viewModel.uiState.observe(lifecycleOwner){
            text = it.number.toString()
            ad = it.ad
        }
    }
    Column(verticalArrangement = Arrangement.SpaceEvenly, modifier = Modifier.fillMaxSize()) {
        Text(text = text, fontWeight = FontWeight.Bold, fontSize = 80.sp)
        Text(text = stringResource(id = if (even) R.string.even else R.string.odd), fontSize = 40.sp)
        Text(text = ad)
    }

}