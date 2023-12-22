package com.test.faurecia.userCases

import com.test.faurecia.data.local.LocalRepository
import com.test.faurecia.userCases.model.AppItem
import javax.inject.Inject

// Use case for saving a list of app items to the database
class SaveAppListUseCase @Inject constructor(
    private val repository: LocalRepository  // Injected via Hilt
) {
    suspend operator fun invoke(list: List<AppItem>) {
        repository.saveList(list)
    }
}