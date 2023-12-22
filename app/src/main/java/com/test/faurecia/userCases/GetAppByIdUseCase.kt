package com.test.faurecia.userCases

import com.test.faurecia.data.local.LocalRepository
import com.test.faurecia.data.local.model.App
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

// Use case for getting an app item by its ID
class GetAppByIdUseCase @Inject constructor(
    private val repository: LocalRepository  // Injected via Hilt
) {
    suspend operator fun invoke(appId: String): Result<App> = withContext(Dispatchers.Default) {
        Result.runCatching { repository.getAppById(appId) }
    }
}