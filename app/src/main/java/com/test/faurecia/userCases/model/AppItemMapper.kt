package com.test.faurecia.userCases.model

import com.test.faurecia.data.remote.model.AppDTO
import com.test.faurecia.data.remote.model.ListAppsResponseDTO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AppItemMapper @Inject constructor() {

    suspend operator fun invoke(appList: ListAppsResponseDTO): List<AppItem> =
        withContext(Dispatchers.Default) {
            appList.responses
                .listApps
                .datasets
                .all
                .data
                .list.map { map(it) }
        }

    private fun map(appItem: AppDTO) = AppItem(
        name = appItem.name,
        icon = appItem.icon,
        graphic = appItem.graphic,
        rating = appItem.rating
    )

}