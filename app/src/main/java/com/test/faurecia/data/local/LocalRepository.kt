package com.test.faurecia.data.local

import com.test.faurecia.userCases.model.AppItem

interface LocalRepository {
    suspend fun getAppById(appId: String): AppItem
    suspend fun saveList(list: List<AppItem>)
}