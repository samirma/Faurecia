package com.test.faurecia.data.local

import com.test.faurecia.data.local.model.App
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


// Implementation of the LocalRepository interface using AppItemDao
class LocalRepositoryImpl @Inject constructor(private val appItemDao: AppItemDao) :
    LocalRepository {

    // Get an app item by its ID
    override suspend fun getAppById(appId: String): App {
        return appItemDao.getAppById(appId)
    }

    // Get the list of all app items
    override fun getAppList(): Flow<List<App>> {
        return appItemDao.getAppList()
    }

    // Save a list of app items to the database
    override suspend fun saveList(list: List<App>) {
        appItemDao.insertAll(list)
    }
}
