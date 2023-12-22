package com.test.faurecia.userCases

import com.test.faurecia.data.local.LocalRepository
import com.test.faurecia.data.local.model.App
import javax.inject.Inject

// Use case for saving a list of app items to the database
class SaveAppListUseCase @Inject constructor(
    private val repository: LocalRepository
) {
    suspend operator fun invoke(list: List<App>) {
        repository.saveList(list)
    }
}