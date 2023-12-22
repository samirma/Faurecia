package com.test.faurecia.userCases

import com.test.faurecia.data.local.LocalRepository
import com.test.faurecia.data.local.model.App
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

// Use case for getting the list of apps from the local database
class GetLocalAppsListUseCase @Inject constructor(
    private val repository: LocalRepository
) {

    operator fun invoke(): Flow<List<App>> {
        return repository.getAppList()
    }
}
