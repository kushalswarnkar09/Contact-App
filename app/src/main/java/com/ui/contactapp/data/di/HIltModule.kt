package com.ui.contactapp.data.di

import android.app.Application
import androidx.room.Room
import com.ui.contactapp.data.database.ContactDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
object HIltModule {

    @Provides
    fun provideDatabase(application: Application) : ContactDatabase{
        return Room.databaseBuilder(
            application.baseContext,
            ContactDatabase::class.java,
            "contacts_db"
        ).fallbackToDestructiveMigration().build()
    }
}



















