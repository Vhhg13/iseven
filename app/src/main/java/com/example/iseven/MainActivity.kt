package com.example.iseven

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.iseven.ui.composables.CheckScreen
import com.example.iseven.ui.composables.ImageScreen
import com.example.iseven.ui.composables.KnownNumberScreen
import com.example.iseven.ui.composables.SeeNumberScreen
import com.example.iseven.ui.ui.theme.IsEvenTheme
import com.example.iseven.util.Routes
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            IsEvenTheme {
                val navController = rememberNavController()
                val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
                var currentScreen by rememberSaveable {
                    mutableStateOf(Routes.CHECK)
                }
                val scope = rememberCoroutineScope()
                ModalNavigationDrawer(
                    drawerContent = {
                        ModalDrawerSheet {
                            NavigationDrawerItem(
                                label = { Text("Check evenness") },
                                selected = currentScreen == Routes.CHECK,
                                onClick = {
                                    if(currentScreen != Routes.CHECK) {
                                        currentScreen = Routes.CHECK
                                        navController.navigate(currentScreen)
                                    }
                                    scope.launch { drawerState.close() }
                                })
                            NavigationDrawerItem(
                                label = { Text("See known numbers") },
                                selected = currentScreen == Routes.KNOWN || currentScreen == Routes.SEE,
                                onClick = {
                                    if(currentScreen != Routes.KNOWN) {
                                        currentScreen = Routes.KNOWN
                                        navController.navigate(currentScreen)
                                    }
                                    scope.launch { drawerState.close() }
                                })
                            NavigationDrawerItem(
                                label = { Text("Download an image") },
                                selected = currentScreen == Routes.IMAGE,
                                onClick = {
                                    if(currentScreen != Routes.IMAGE) {
                                        currentScreen = Routes.IMAGE
                                        navController.navigate(currentScreen)
                                    }
                                    scope.launch { drawerState.close() }
                                })
                        }
                    },
                    drawerState = drawerState
                ) {
                    Scaffold(
                        topBar = {
                            TopAppBar(title = { Text(stringResource(id = R.string.app_name))})
                        },
                        bottomBar = {
                            BottomNavigation {
                                BottomNavigationItem(
                                    label = { Text("Check evenness") },
                                    icon = { Icon(
                                        imageVector = Icons.Default.Add,
                                        contentDescription = ""
                                    )},
                                    selected = currentScreen == Routes.CHECK,
                                    onClick = {
                                        if(currentScreen != Routes.CHECK) {
                                            currentScreen = Routes.CHECK
                                            navController.navigate(currentScreen)
                                        }
                                    })
                                BottomNavigationItem(
                                    label = { Text("See known numbers") },
                                    icon = { Icon(
                                        imageVector = Icons.Default.Check,
                                        contentDescription = ""
                                    )},
                                    selected = currentScreen == Routes.KNOWN || currentScreen == Routes.SEE,
                                    onClick = {
                                        if(currentScreen != Routes.KNOWN) {
                                            currentScreen = Routes.KNOWN
                                            navController.navigate(currentScreen)
                                        }
                                    })
                                BottomNavigationItem(
                                    label = { Text("Download an image") },
                                    icon = { Icon(imageVector = Icons.Default.Email, contentDescription = "") },
                                    selected = currentScreen == Routes.IMAGE,
                                    onClick = {
                                        if(currentScreen != Routes.IMAGE) {
                                            currentScreen = Routes.IMAGE
                                            navController.navigate(currentScreen)
                                        }
                                    })
                            }
                        },
                        //modifier = Modifier.padding(10.dp)
                    ) {
                        NavHost(
                            navController = navController,
                            startDestination = Routes.CHECK,
                            modifier = Modifier.padding(top = it.calculateTopPadding(), bottom = it.calculateBottomPadding(), start = 10.dp, end = 10.dp),
                        ){
                            composable(Routes.CHECK){
                                CheckScreen()
                            }
                            composable(Routes.SEE + "/{n}", arguments = listOf(
                                navArgument(name = "n"){
                                    type = NavType.IntType
                                }
                            )){
                                SeeNumberScreen()
                            }
                            composable(Routes.KNOWN){
                                KnownNumberScreen(navigateToSeen = {number ->
                                    navController.navigate("${Routes.SEE}/$number")
                                })
                            }
                            composable(Routes.IMAGE){
                                ImageScreen()
                            }
                        }
                    }
                }

            }
        }
    }
}