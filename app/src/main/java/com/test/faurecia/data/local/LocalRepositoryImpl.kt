package com.test.faurecia.data.local

import com.test.faurecia.userCases.model.AppItem

// Implementation of the LocalRepository interface using AppItemDao
class LocalRepositoryImpl(private val appItemDao: AppItemDao) : LocalRepository {
    // Get an app item by its ID
    override suspend fun getAppById(appId: String): AppItem {
        return appItemDao.getAppById(appId)
    }

    // Save a list of app items to the database
    override suspend fun saveList(list: List<AppItem>) {
        appItemDao.insertAll(list)
    }
}
