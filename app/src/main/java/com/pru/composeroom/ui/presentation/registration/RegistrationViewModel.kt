package com.pru.composeroom.ui.presentation.registration

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pru.composeroom.models.Patient
import com.pru.composeroom.repository.RepositorySDK
import com.pru.composeroom.utils.DateUtils
import com.pru.composeroom.utils.Enumerator
import com.pru.composeroom.utils.ScreenRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegistrationViewModel @Inject constructor(private val repositorySDK: RepositorySDK) :
    ViewModel() {

    var name by mutableStateOf("")

    var email by mutableStateOf<String?>(null)

    var phone by mutableStateOf("")

    var address by mutableStateOf("")

    var reasonForReg by mutableStateOf("")

    var openGenderDropdown by mutableStateOf(false)

    var openMaritalStatusDropdown by mutableStateOf(false)

    var selectedGender by mutableStateOf<Enumerator.Gender>(Enumerator.Gender.None)

    var selectedMaritalStatus by mutableStateOf<Enumerator.MaritalStatus>(Enumerator.MaritalStatus.None)

    var selectedDOB by mutableStateOf(DateUtils.MyDateValue())

    val genderList =
        listOf(Enumerator.Gender.Male, Enumerator.Gender.Female, Enumerator.Gender.Other)

    val maritalStatusList =
        listOf(
            Enumerator.MaritalStatus.Single,
            Enumerator.MaritalStatus.Married,
            Enumerator.MaritalStatus.Divorced,
            Enumerator.MaritalStatus.LegallySeparated,
            Enumerator.MaritalStatus.Widowed
        )

    val snackBarMessageState = MutableSharedFlow<String>()

    val screenNavigation = MutableSharedFlow<ScreenRoute>()

    private suspend fun validateForm(): Boolean {
        var isValid = true
        when {
            name.trim().isEmpty() -> {
                snackBarMessageState.emit("Please enter Patient name")
                isValid = false
            }
            phone.trim().isEmpty() -> {
                snackBarMessageState.emit("Please enter Patient phone")
                isValid = false
            }
            selectedGender == Enumerator.Gender.None -> {
                snackBarMessageState.emit("Please select Gender")
                isValid = false
            }
            selectedDOB.date == null -> {
                snackBarMessageState.emit("Please select Date of Birth")
                isValid = false
            }
            selectedMaritalStatus == Enumerator.MaritalStatus.None -> {
                snackBarMessageState.emit("Please select Marital Status")
                isValid = false
            }
            address.trim().isEmpty() -> {
                snackBarMessageState.emit("Please select Address")
                isValid = false
            }
            reasonForReg.trim().isEmpty() -> {
                snackBarMessageState.emit("Please select Reason")
                isValid = false
            }
        }
        return isValid
    }

    fun insertPatient() {
        viewModelScope.launch(Dispatchers.IO) {
            if (validateForm()) {
                val patient = Patient(
                    patientName = name,
                    patientEmail = email,
                    patientPhone = phone,
                    gender = selectedGender.value,
                    dateOfBirth = selectedDOB.date!!,
                    maritalStatus = selectedMaritalStatus.value,
                    address = address,
                    reasonForReg = reasonForReg
                )
                repositorySDK.insertPatient(patient = patient)
                screenNavigation.emit(ScreenRoute.PopBack)
            }
        }
    }
}