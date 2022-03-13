package com.pru.composeroom.repository

import com.pru.composeroom.R
import com.pru.composeroom.dao.PatientDao
import com.pru.composeroom.models.Patient
import com.pru.composeroom.utils.UIState
import com.pru.composeroom.utils.resource.ResourceProvider
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow

class RepositorySDK(
    private val patientDao: PatientDao,
    private val resourceProvider: ResourceProvider
) {

    suspend fun insertPatient(patient: Patient) {
        patientDao.insertAll(patient)
    }

    suspend fun getAllUsersFlow(): Flow<UIState<List<Patient>>> = flow<UIState<List<Patient>>> {
        emit(UIState.Loading())
        patientDao.getAllPatientsFlow().collect { users ->
            if (users.isEmpty()) {
                emit(UIState.Error(message = resourceProvider.obtainString(R.string.no_data_users)))
            } else {
                emit(UIState.Success(users))
            }
        }
    }
}