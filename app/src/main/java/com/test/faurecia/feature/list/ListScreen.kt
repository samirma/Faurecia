package com.test.faurecia.feature.list

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.test.faurecia.R
import com.test.faurecia.Route
import com.test.faurecia.common.ui.ErrorMessage
import com.test.faurecia.common.ui.LoadingIndicator
import com.test.faurecia.common.ui.RatingBar
import com.test.faurecia.feature.list.model.AppItemView
import com.test.faurecia.feature.list.model.ListState
import com.test.faurecia.feature.list.model.ListState.Error
import com.test.faurecia.feature.list.model.ListState.Loaded
import com.test.faurecia.feature.list.model.ListState.Loading


@Composable
fun AppListNavigation(navController: NavController, viewModel: ListViewModel = hiltViewModel()) {

    val state by viewModel.state.collectAsState()

    LaunchedEffect(viewModel) {
        viewModel.fetchAppsList()
    }

    AppListScreen(state) { app: AppItemView ->
        navController.navigate(Route.getDetailUrlById(app.id))
    }

}

@Composable
fun AppListScreen(state: ListState, onAppSelected: (AppItemView) -> Unit) {

    when (state) {
        is Loading -> {
            LoadingIndicator()
        }

        is Error -> {
            ErrorMessage(stringResource(R.string.an_error_occurred))
        }

        is Loaded -> {
            val appList = state.appList
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(appList) { app ->
                    AppCard(onAppSelected, app)
                }
            }
        }
    }
}

@Composable
private fun AppCard(
    onAppSelected: (AppItemView) -> Unit,
    app: AppItemView
) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .clickable {
                onAppSelected(app)
            },
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Image(
                painter = rememberImagePainter(data = app.icon),
                contentDescription = "App Icon",
                modifier = Modifier.size(64.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(
                    text = app.name,
                    style = MaterialTheme.typography.headlineMedium
                )
                Spacer(modifier = Modifier.height(4.dp))
                RatingBar(value = app.rating)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewAppListScreenLoading() {
    AppListScreen(state = Loading, onAppSelected = {})
}

@Preview(showBackground = true)
@Composable
fun PreviewAppListScreenError() {
    AppListScreen(state = Error, onAppSelected = {})
}

@Preview(showBackground = true)
@Composable
fun PreviewAppListScreenLoaded() {
    val appList = listOf(
        AppItemView(
            id = "1",
            name = "App 1",
            icon = "https://pool.img.aptoide.com/appupdater/bbcd505528c1d0fbeb34cb590b461c8e_icon.png",
            rating = 1
        ),
        AppItemView(
            id = "1",
            name = "App 2",
            icon = "https://pool.img.aptoide.com/oppo-api/ce4a21df87884b07f8090ead72c81bb2_icon.png",
            rating = 2
        ),
        AppItemView(
            id = "1",
            name = "App 3",
            icon = "\"https://pool.img.aptoide.com/appupdater/c4e0ea4ae7fa4cfdeaafc751951a6130_icon.png",
            rating = 3
        ),
        AppItemView(
            id = "1",
            name = "App 4",
            icon = "https://pool.img.aptoide.com/appupdater/4a627525190c24c39a681a5bb63f07df_icon.jpg\"",
            rating = 4
        ),
    )
    AppListScreen(state = Loaded(appList), onAppSelected = {})
}
