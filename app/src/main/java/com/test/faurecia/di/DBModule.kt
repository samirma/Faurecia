package com.test.faurecia.di

import android.content.Context
import androidx.room.Room
import com.test.faurecia.data.local.AppDatabase
import com.test.faurecia.data.local.AppItemDao
import com.test.faurecia.data.local.LocalRepository
import com.test.faurecia.data.local.LocalRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

// Hilt module for providing the necessary dependencies
@Module
@InstallIn(SingletonComponent::class)
object DBModule {

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "app_database"
        ).build()
    }

    @Provides
    fun provideAppItemDao(database: AppDatabase): AppItemDao {
        return database.appItemDao()
    }

}

@Module
@InstallIn(SingletonComponent::class)
abstract class DBRepositoryModule {
    @Binds
    abstract fun bindAppRepository(impl: LocalRepositoryImpl): LocalRepository
}
