package com.test.faurecia.userCases

import com.test.faurecia.data.remote.RemoteRepository
import com.test.faurecia.data.remote.model.ListAppsResponseDTO
import com.test.faurecia.userCases.model.AppItem
import com.test.faurecia.userCases.model.AppItemMapper
import javax.inject.Inject

class GetAppsListUseCase @Inject constructor(
    private val remoteRepository: RemoteRepository,
    private val mapper: AppItemMapper
) {
    suspend operator fun invoke(): List<AppItem> {
        val responseDTOResult: Result<ListAppsResponseDTO> = remoteRepository.getApps()
        return mapper(responseDTOResult.getOrThrow())
    }
}