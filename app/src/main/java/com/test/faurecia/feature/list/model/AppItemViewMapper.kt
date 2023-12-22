package com.test.faurecia.feature.list.model

import com.test.faurecia.userCases.model.AppItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AppItemViewMapper @Inject constructor() {

    suspend operator fun invoke(appList: List<AppItem>): List<AppItemView> =
        withContext(Dispatchers.Default) {
            appList.map { map(it) }
        }

    private fun map(appItem: AppItem): AppItemView {
        return AppItemView(
            name = appItem.name,
            icon = appItem.icon,
            graphic = appItem.banner,
            rating = appItem.rating.toInt()
        )
    }

}