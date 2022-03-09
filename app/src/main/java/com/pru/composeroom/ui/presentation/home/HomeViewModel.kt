package com.pru.composeroom.ui.presentation.home

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pru.composeroom.models.Patient
import com.pru.composeroom.repository.RepositorySDK
import com.pru.composeroom.utils.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repositorySDK: RepositorySDK
) : ViewModel() {
    var users = mutableStateOf<UIState<List<Patient>>>(UIState.None())

    fun getUsers() {
        viewModelScope.launch(Dispatchers.IO) {
            repositorySDK.getAllUsersFlow().collect {
                users.value = it
            }
        }
    }
}