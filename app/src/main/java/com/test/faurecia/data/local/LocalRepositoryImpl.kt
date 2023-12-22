package com.test.faurecia.data.local

import com.test.faurecia.data.local.model.App
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject


// Implementation of the LocalRepository interface using AppItemDao
class LocalRepositoryImpl @Inject constructor(
    private val appItemDao: AppItemDao
) : LocalRepository {

    // Get an app by its ID
    override suspend fun getAppById(appId: String): App = withContext(Dispatchers.Default) {
         appItemDao.getAppById(appId)
    }

    // Get the list of all apps
    override fun getAppList(): Flow<List<App>> {
        return appItemDao.getAppList()
    }

    // Save a list of apps to the database
    override suspend fun saveList(list: List<App>) = withContext(Dispatchers.Default) {
        appItemDao.insertAll(list)
    }
}
