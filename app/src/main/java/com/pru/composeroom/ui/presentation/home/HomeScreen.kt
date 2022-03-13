package com.pru.composeroom.ui.presentation.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.pru.composeroom.R
import com.pru.composeroom.utils.Global.extractBitmap
import com.pru.composeroom.utils.ScreenRoute
import com.pru.composeroom.utils.UIState

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
                            Card(
                                elevation = 10.dp,
                                shape = RoundedCornerShape(10.dp),
                                modifier = Modifier.padding(10.dp)
                            ) {
                                ListItem(secondaryText = {
                                    Text(
                                        text = result.data[index].patientName,
                                        fontSize = 18.sp, fontWeight = FontWeight.SemiBold
                                    )
                                }, icon = {
                                    extractBitmap(result.data[index].image)?.let {
                                        Image(
                                            bitmap = it.asImageBitmap(),
                                            contentDescription = null,
                                            modifier = Modifier
                                                .clip(CircleShape)
                                                .size(50.dp),
                                            alignment = Alignment.Center,
                                            contentScale = ContentScale.Crop
                                        )
                                    } ?: Image(
                                        painter = painterResource(id = R.drawable.user),
                                        contentDescription = null,
                                        modifier = Modifier
                                            .clip(CircleShape)
                                            .size(50.dp),
                                        alignment = Alignment.Center,
                                        contentScale = ContentScale.Crop
                                    )
                                }) {
                                    Text(
                                        text = result.data[index].patientPhone,
                                        fontSize = 16.sp, fontWeight = FontWeight.Normal
                                    )
                                }
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