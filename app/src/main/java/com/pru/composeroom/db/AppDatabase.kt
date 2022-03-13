package com.pru.composeroom.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.pru.composeroom.dao.PatientDao
import com.pru.composeroom.models.Patient
import com.pru.composeroom.utils.typeconverters.DateConverter

@Database(entities = [Patient::class], version = 1)
@TypeConverters(DateConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun patientDao(): PatientDao
}