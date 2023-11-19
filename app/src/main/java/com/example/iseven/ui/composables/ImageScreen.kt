package com.example.iseven.ui.composables

import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.LifecycleOwner
import coil.compose.rememberAsyncImagePainter
import com.example.iseven.R
import com.example.iseven.ui.viewmodels.ImageFragmentViewModel

@Composable
fun ImageScreen(
    viewModel: ImageFragmentViewModel = hiltViewModel(),
    lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current
) {
    var images by remember{
        mutableStateOf(listOf<Bitmap>())
    }
    var image by remember{
        mutableStateOf(ImageBitmap(1, 1).asAndroidBitmap())
    }
    LaunchedEffect(key1 = true){
        viewModel.images.observe(lifecycleOwner){
            images = it
        }
        viewModel.uiState.observe(lifecycleOwner){
            image = it
            viewModel.save(it)
        }
    }
    var link by rememberSaveable {
        mutableStateOf("")
    }

    ConstraintLayout(modifier = Modifier.fillMaxWidth()) {
        val (lst, linkField, dlButton, imageComposable) = createRefs()

        LazyRow(modifier = Modifier
            .height(105.dp)
            .constrainAs(lst) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
            .clickable {
                viewModel.clearImages()
            },
            horizontalArrangement = Arrangement.Start
        ){
            itemsIndexed(images){ ind, item ->
                Image(painter = rememberAsyncImagePainter(model = item), contentDescription = null, modifier = Modifier.size(100.dp))
            }
        }


        OutlinedTextField(value = link, onValueChange = { link = it }, modifier = Modifier.constrainAs(linkField){
            top.linkTo(lst.bottom)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        })

        Button(onClick = {
            viewModel.load(link)
        }, modifier = Modifier.constrainAs(dlButton){
            top.linkTo(linkField.bottom)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }
        ) {
            Text(stringResource(id = R.string.download_image))
        }
        Image(painter = rememberAsyncImagePainter(model = image), contentDescription = null, modifier = Modifier.constrainAs(imageComposable){
            top.linkTo(dlButton.bottom)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        })
    }
}