package com.test.faurecia.data.remote

import com.test.faurecia.data.remote.model.ListAppsResponseDTO

interface RemoteRepository {
    suspend fun getApps(): Result<ListAppsResponseDTO>
}
