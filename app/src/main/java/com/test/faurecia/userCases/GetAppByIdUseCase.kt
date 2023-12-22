package com.test.faurecia.userCases

import com.test.faurecia.data.local.LocalRepository
import com.test.faurecia.userCases.model.AppItem
import javax.inject.Inject

// Use case for getting an app item by its ID
class GetAppByIdUseCase @Inject constructor(
    private val repository: LocalRepository  // Injected via Hilt
) {
    suspend operator fun invoke(appId: String): AppItem {
        return repository.getAppById(appId)
    }
}