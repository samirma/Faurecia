package com.test.faurecia.feature.detail.model

import com.test.faurecia.data.local.model.App
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AppDetailMapper @Inject constructor() {

    suspend operator fun invoke(app: App): AppDetailView = withContext(Dispatchers.Default) {
        AppDetailView(
            id = app.id,
            name = app.name,
            icon = app.icon,
            banner = app.banner,
            rating = app.rating.toInt()
        )
    }

}