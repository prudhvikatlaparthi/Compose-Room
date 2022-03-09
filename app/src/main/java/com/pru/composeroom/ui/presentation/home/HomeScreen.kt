package com.pru.composeroom.ui.presentation.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.pru.composeroom.utils.ScreenRoute
import com.pru.composeroom.utils.UIState
import com.pru.composeroom.R

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HomeScreen(navController: NavController, viewModel: HomeViewModel = hiltViewModel()) {
    LaunchedEffect(key1 = Unit) {
        viewModel.getUsers()
    }
    Scaffold(floatingActionButton = {
        FloatingActionButton(onClick = {
            navController.navigate(ScreenRoute.RegistrationScreen.routeName)
        }) {
            Icon(Icons.Filled.Add, contentDescription = "")
        }
    }) {
        Box(modifier = Modifier.fillMaxSize()) {
            when (val result = viewModel.users.value) {
                is UIState.Loading -> {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .width(30.dp)
                            .height(30.dp)
                            .align(Alignment.Center)
                    )
                }
                is UIState.Success -> {
                    LazyColumn() {
                        items(result.data!!.size) { index ->
                            ListItem(secondaryText = {
                                Text(
                                    text = result.data[index].patientName
                                        ?: ""
                                )
                            }) {
                                Text(
                                    text = result.data[index].patientPhone
                                        ?: ""
                                )
                            }
                        }
                    }
                }
                is UIState.Error -> {
                    Text(
                        text = result.message ?: stringResource(id = R.string.no_data_users),
                        modifier = Modifier.align(
                            Alignment.Center
                        )
                    )
                }
                else -> {}
            }
        }
    }

}