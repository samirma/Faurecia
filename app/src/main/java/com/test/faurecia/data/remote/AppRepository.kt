package com.test.faurecia.data.remote

import com.test.faurecia.data.remote.model.ListAppsResponseDTO

interface AppRepository {

    suspend fun getApps(): Result<ListAppsResponseDTO>
}
