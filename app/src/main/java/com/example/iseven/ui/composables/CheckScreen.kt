package com.example.iseven.ui.composables

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.LifecycleOwner
import com.example.iseven.R
import com.example.iseven.ui.viewmodels.CheckFragmentViewModel

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun CheckScreen(
    viewModel: CheckFragmentViewModel = hiltViewModel(),
    lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current
){
    var number by rememberSaveable { mutableStateOf("") }
    var ad by rememberSaveable { mutableStateOf("") }
    var evenness by rememberSaveable {
        mutableStateOf(R.string.empty)
    }
    val kbdController = LocalSoftwareKeyboardController.current
    LaunchedEffect(key1 = true){
        viewModel.uiState.observe(lifecycleOwner){
            number = it.number.toString()
            ad = it.ad
            evenness = if(it.isEven) R.string.even else R.string.odd
        }
    }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        OutlinedTextField(
            value = number,
            onValueChange = { number = it },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(onDone = {
                kbdController?.hide()
                try {
                    viewModel.check(number.toInt())
                }catch(_: NumberFormatException){}
            })
        )
        Button(onClick = {
            try {
                viewModel.check(number.toInt())
            }catch(_: NumberFormatException){}
        }) {
            Text(text = stringResource(id = R.string.check_evenness))
        }
        Text(text = stringResource(evenness))
        Text(text = ad)
    }

}