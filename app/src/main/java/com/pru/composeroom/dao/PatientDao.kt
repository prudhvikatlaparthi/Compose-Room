package com.pru.composeroom.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.pru.composeroom.models.Patient
import kotlinx.coroutines.flow.Flow

@Dao
interface PatientDao {

    @Query("SELECT * FROM tbl_patient ORDER BY pid DESC")
    fun getAllPatientsFlow(): Flow<List<Patient>>

    @Query("SELECT * FROM tbl_patient WHERE pid IN (:userIds)")
    fun loadAllByIds(userIds: IntArray): Flow<List<Patient>>

    @Query(
        "SELECT * FROM tbl_patient WHERE patient_name LIKE :patientName AND " +
                "patient_phone LIKE :patientPhone LIMIT 1"
    )
    fun findByName(patientName: String, patientPhone: String): Flow<Patient>

    @Insert
    suspend fun insertAll(vararg patients: Patient)

    @Delete
    suspend fun delete(patient: Patient)
}