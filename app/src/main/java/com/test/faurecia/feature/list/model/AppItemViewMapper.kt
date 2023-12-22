package com.test.faurecia.feature.list.model

import com.test.faurecia.data.local.model.App
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AppItemViewMapper @Inject constructor() {

    suspend operator fun invoke(appList: List<App>): List<AppItemView> =
        withContext(Dispatchers.Default) {
            appList.map { map(it) }
        }

    private fun map(appItem: App): AppItemView {
        return AppItemView(
            id = appItem.id,
            name = appItem.name,
            icon = appItem.icon,
            rating = appItem.rating.toInt()
        )
    }

}