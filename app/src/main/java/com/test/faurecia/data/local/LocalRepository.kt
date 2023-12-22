package com.test.faurecia.data.local

import com.test.faurecia.data.local.model.App
import kotlinx.coroutines.flow.Flow


interface LocalRepository {
    suspend fun getAppById(appId: String): App
    fun getAppList(): Flow<List<App>>
    suspend fun saveList(list: List<App>)
}