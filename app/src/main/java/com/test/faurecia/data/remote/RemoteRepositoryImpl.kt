package com.test.faurecia.data.remote

import com.test.faurecia.data.remote.model.ListAppsResponseDTO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RemoteRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
) : RemoteRepository {
    override suspend fun getApps(): Result<ListAppsResponseDTO> = withContext(Dispatchers.Default) {
        return@withContext Result.runCatching { apiService.listApps() }
    }
}