package com.test.faurecia.userCases

import com.test.faurecia.data.local.model.App
import com.test.faurecia.data.remote.RemoteRepository
import com.test.faurecia.data.remote.model.ListAppsResponseDTO
import com.test.faurecia.userCases.model.AppItemMapper
import javax.inject.Inject

class GetRemoteAppsListUseCase @Inject constructor(
    private val remoteRepository: RemoteRepository,
    private val mapper: AppItemMapper
) {
    suspend operator fun invoke(): List<App> {
        val responseDTOResult: Result<ListAppsResponseDTO> = remoteRepository.getApps()
        return mapper(responseDTOResult.getOrThrow())
    }
}