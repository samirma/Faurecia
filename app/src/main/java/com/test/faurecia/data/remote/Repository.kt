package com.test.faurecia.data.remote

import com.test.faurecia.data.remote.model.ListAppsResponseDTO

interface Repository {
    suspend fun getApps(): Result<ListAppsResponseDTO>
}
