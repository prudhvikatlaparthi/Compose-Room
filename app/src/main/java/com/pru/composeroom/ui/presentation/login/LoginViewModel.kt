package com.pru.composeroom.ui.presentation.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.pru.composeroom.repository.RepositorySDK
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val repositorySDK: RepositorySDK) : ViewModel() {
    var email by mutableStateOf("")

    var password by mutableStateOf("")

    var passwordVisible by mutableStateOf(false)

}