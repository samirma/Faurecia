package com.test.faurecia.di

import com.test.faurecia.data.local.AppDatabase
import com.test.faurecia.data.local.AppItemDao
import com.test.faurecia.data.local.LocalRepository
import com.test.faurecia.data.local.LocalRepositoryImpl
import com.test.faurecia.data.remote.RemoteRepository
import com.test.faurecia.data.remote.RepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

// Hilt module for providing the necessary dependencies
@Module
@InstallIn(SingletonComponent::class)
object LocalModule {
    @Provides
    fun provideAppItemDao(database: AppDatabase): AppItemDao {
        return database.appItemDao()
    }

}

@Module
@InstallIn(SingletonComponent::class)
abstract class LocalRepositoryModule {
    @Binds
    abstract fun bindAppRepository(impl: LocalRepositoryImpl): LocalRepository
}
