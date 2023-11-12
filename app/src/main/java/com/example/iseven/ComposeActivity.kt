package com.example.iseven

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Text
import com.example.iseven.data.model.Evenness
import com.example.iseven.ui.composables.CheckScreen
import com.example.iseven.ui.ui.theme.IsEvenTheme

class ComposeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContent {
//            IsEvenTheme {
//                // A surface container using the 'background' color from the theme
//                CheckScreen(
//                    uiState = Evenness(0, true, ""),
//                    checkEvennessCallback =,
//                    seeKnownCallback = { /*TODO*/ },
//                    context =
//                )
//            }
//        }
    }
}