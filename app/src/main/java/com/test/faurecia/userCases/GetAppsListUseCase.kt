package com.test.faurecia.userCases

import com.test.faurecia.data.local.model.App
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class GetAppsListUseCase @Inject constructor(
    private val getLocalAppsListUseCase: GetLocalAppsListUseCase,
    private val getRemoteAppsListUseCase: GetRemoteAppsListUseCase,
    private val saveAppListUseCase: SaveAppListUseCase
) {
    // This use case first tries to get the list of apps from the local database.
    // If the list is empty, it fetches the list from the remote repository,
    // saves the fetched list to the local database.
    operator fun invoke(): Flow<List<App>> = getLocalAppsListUseCase()
        .onEach {
            if (it.isEmpty()) {
                val remoteAppList = getRemoteAppsListUseCase()
                saveAppListUseCase(remoteAppList)
            }
        }
}

