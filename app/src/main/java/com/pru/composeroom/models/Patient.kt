package com.pru.composeroom.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "tbl_patient")
data class Patient(
    @PrimaryKey(autoGenerate = true) val pid: Int? = null,
    @ColumnInfo(name = "patient_name") val patientName: String,
    @ColumnInfo(name = "patient_email") val patientEmail: String?,
    @ColumnInfo(name = "patient_phone") val patientPhone: String,
    @ColumnInfo(name = "gender") val gender: String,
    @ColumnInfo(name = "date_of_birth") val dateOfBirth: Date,
    @ColumnInfo(name = "marital_status") val maritalStatus: String,
    @ColumnInfo(name = "address") val address: String,
    @ColumnInfo(name = "reason_for_reg") val reasonForReg: String,
    @ColumnInfo(typeAffinity = ColumnInfo.BLOB) var image: ByteArray? = null
)
