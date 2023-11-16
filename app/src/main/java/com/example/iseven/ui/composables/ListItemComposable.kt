package com.example.iseven.ui.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.iseven.R
import com.example.iseven.data.model.KnownListItem
import androidx.compose.material3.Icon

@Composable
fun ListItemComposable(uiState: KnownListItem, seeNumber: () -> Unit, deleteNumber: () -> Unit){
    Box(modifier = Modifier.padding(start = 10.dp)) {
        Column(modifier = Modifier
            .clickable { seeNumber() }
            .fillMaxWidth()
            .padding(15.dp)
            .align(Alignment.CenterStart)) {
            Text(text = uiState.number.toString(), fontSize = 25.sp, fontWeight = FontWeight.Bold)
            Text(text = stringResource(id = if(uiState.parity) R.string.even else R.string.odd), fontSize = 15.sp)
        }
        IconButton(onClick = deleteNumber, Modifier.padding(end = 15.dp).align(Alignment.CenterEnd).clickable { deleteNumber() }) {
            Icon(imageVector = Icons.Filled.Delete, contentDescription = null, Modifier.size(35.dp))
        }
    }
}