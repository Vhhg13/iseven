package com.example.iseven.ui.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.iseven.R
import com.example.iseven.data.model.KnownListItem

@Composable
fun ListItemComposable(uiState: KnownListItem, onClick: () -> Unit){
    Row(modifier = Modifier.fillMaxWidth().clickable { onClick() }) {
        Column(horizontalAlignment = Alignment.Start) {
            Text(text = uiState.number.toString(), fontSize = 20.sp)
            Text(text = stringResource(id = if(uiState.parity) R.string.even else R.string.odd), fontSize = 20.sp)
        }
        Box(contentAlignment = Alignment.CenterEnd){Text(text = "X", fontSize = 25.sp, modifier = Modifier.padding(10.dp))}
    }

}