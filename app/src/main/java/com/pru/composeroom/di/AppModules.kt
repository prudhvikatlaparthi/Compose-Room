package com.pru.composeroom.di

import android.app.Application
import androidx.room.Room
import com.pru.composeroom.dao.PatientDao
import com.pru.composeroom.db.AppDatabase
import com.pru.composeroom.repository.RepositorySDK
import com.pru.composeroom.utils.resource.ResourceProvider
import com.pru.composeroom.utils.Constants.kDBNAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModules {

    @Provides
    @Singleton
    fun provideAppDataBase(app: Application): AppDatabase {
        return Room.databaseBuilder(
            app,
            AppDatabase::class.java, kDBNAME
        ).fallbackToDestructiveMigration().build()
    }

    @Provides
    @Singleton
    fun provideUserDao(appDatabase: AppDatabase): PatientDao {
        return appDatabase.patientDao()
    }

    @Provides
    @Singleton
    fun provideResourceProvider(app: Application) =
        ResourceProvider(context = app)

    @Provides
    @Singleton
    fun provideRepositorySDK(patientDao: PatientDao, resourceProvider: ResourceProvider) = RepositorySDK(
        patientDao = patientDao,
        resourceProvider = resourceProvider
    )
}