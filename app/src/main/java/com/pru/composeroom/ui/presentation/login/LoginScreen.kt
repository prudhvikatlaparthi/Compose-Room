package com.pru.composeroom.ui.presentation.login

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.pru.composeroom.ui.theme.MainColor

@Composable
fun LoginScreen(
    navController: NavController,
    viewModel: LoginViewModel = viewModel()
) {
    val focusManager = LocalFocusManager.current

    Scaffold(backgroundColor = MainColor, bottomBar = {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 30.dp, start = 20.dp, end = 20.dp)
        ) {
            Row(horizontalArrangement = Arrangement.Center) {
                Text(text = "Don't have an account? ", color = Color.White)
                Text(
                    text = "Register",
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    modifier = Modifier.clickable {

                    })
            }
            Spacer(modifier = Modifier.height(10.dp))
            Button(
                onClick = {}, colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.White,
                    contentColor = MainColor
                ), shape = RoundedCornerShape(10.dp), modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Sign in", modifier = Modifier.padding(4.dp), fontSize = 16.sp)
            }
        }
    }) {
        Column(
            modifier = Modifier
                .padding(20.dp)
                .fillMaxSize()
        ) {
            Icon(
                Icons.Default.ArrowBack,
                contentDescription = Icons.Default.ArrowBack.toString(),
                tint = Color.White,
                modifier = Modifier.clickable {
                }
            )
            Spacer(modifier = Modifier.fillMaxHeight(0.05f))
            Text(
                text = "Let's sign you in.",
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = "Welcome back.\nYou've been missed!",
                fontSize = 20.sp,
                color = Color.White
            )
            Spacer(modifier = Modifier.fillMaxHeight(0.12f))
            TextField(
                value = viewModel.email,
                onValueChange = {
                    viewModel.email = it
                },
                singleLine = true,
                placeholder = {
                    Text(text = "Phone, Email or Username")
                },
                shape = RoundedCornerShape(20.dp),
                colors = TextFieldDefaults.textFieldColors(
                    textColor = Color.Black,
                    disabledTextColor = Color.Transparent,
                    backgroundColor = Color.White,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                ),
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(
                    onNext = {
                        focusManager.moveFocus(FocusDirection.Down)
                    }
                ),
                trailingIcon = {
                    if ("".isNotEmpty()) {
                        Icon(
                            Icons.Filled.Cancel,
                            contentDescription = "",
                            modifier = Modifier.clickable {
                                viewModel.email = ""
                            })
                    }
                },
            )
            Spacer(modifier = Modifier.height(10.dp))
            TextField(
                value = viewModel.password,
                onValueChange = {
                    viewModel.password = it
                },
                placeholder = {
                    Text(text = "Password")
                },
                singleLine = true,
                shape = RoundedCornerShape(20.dp),
                colors = TextFieldDefaults.textFieldColors(
                    textColor = Color.Black,
                    disabledTextColor = Color.Transparent,
                    backgroundColor = Color.White,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                ),
                visualTransformation = if (viewModel.passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                modifier = Modifier.fillMaxWidth(),
                trailingIcon = {
                    Row(modifier = Modifier.padding(vertical = 10.dp)) {
                        if (viewModel.password.isNotEmpty()) {
                            Icon(
                                Icons.Filled.Cancel,
                                contentDescription = "",
                                modifier = Modifier.clickable {
                                    viewModel.password = ""
                                })
                        }
                        Spacer(modifier = Modifier.width(5.dp))
                        Icon(
                            if (viewModel.passwordVisible) Icons.Filled.VisibilityOff else Icons.Filled.Visibility,
                            contentDescription = "",
                            modifier = Modifier.clickable {
                                viewModel.passwordVisible = !viewModel.passwordVisible
                            })
                        Spacer(modifier = Modifier.width(5.dp))
                    }
                },
                keyboardActions = KeyboardActions(
                    onDone = {
                        focusManager.clearFocus()
                    }
                ),
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Done
                )
            )

        }
    }
}