package com.example.iseven.ui.composables

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.LifecycleOwner
import com.example.iseven.data.model.KnownListItem
import com.example.iseven.ui.viewmodels.KnownNumbersFragmentViewModel

@Composable
fun KnownNumberScreen(
    navigateToSeen: (Int) -> Unit,
    viewModel: KnownNumbersFragmentViewModel = hiltViewModel(),
    lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current
) {
    var items by remember {
        mutableStateOf(listOf<KnownListItem>())
    }
    LaunchedEffect(key1 = true){
        viewModel.uiState.observe(lifecycleOwner){
            items = it
        }
    }
    LazyColumn(modifier = Modifier.fillMaxSize()){
        itemsIndexed(items){ _, item ->
            ListItemComposable(uiState = item, onClick = {
                navigateToSeen(item.number)
            })
        }
    }
}