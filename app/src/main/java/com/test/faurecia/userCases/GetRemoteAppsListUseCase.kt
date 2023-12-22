package com.test.faurecia.userCases

import com.test.faurecia.data.remote.RemoteRepository
import com.test.faurecia.userCases.model.AppItemMapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetRemoteAppsListUseCase @Inject constructor(
    private val remoteRepository: RemoteRepository,
    private val mapper: AppItemMapper
) {
    suspend operator fun invoke() = withContext(Dispatchers.Default) {
        return@withContext Result.runCatching {
            mapper(
                appList = remoteRepository.getApps().getOrThrow()
            )
        }
    }
}