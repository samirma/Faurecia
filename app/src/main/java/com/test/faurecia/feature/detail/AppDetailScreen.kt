package com.test.faurecia.feature.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberImagePainter
import com.test.faurecia.feature.detail.model.DetailState
import androidx.compose.ui.tooling.preview.Preview
import com.test.faurecia.common.ui.RatingBar
import com.test.faurecia.feature.detail.model.AppDetailView

@Composable
fun AppDetailNavigation(appId: String, viewModel: AppDetailViewModel = hiltViewModel()) {

    viewModel.fetchApp(appId)

    val app by viewModel.state.collectAsState()

    AppDetailScreen(app)

}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppDetailScreen(state: DetailState) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "App Detail") },
                navigationIcon = {
                    IconButton(onClick = { /* Handle back action here */ }) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { innerPadding ->
        val modifier = Modifier.padding(innerPadding)

        Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            when (state) {
                is DetailState.Loading -> {
                    CircularProgressIndicator()
                }
                is DetailState.Error -> {
                    Text("An error occurred")
                }
                is DetailState.Loaded -> {
                    val app = state.app
                    Column(modifier = Modifier.padding(16.dp)) {
                        app.banner?.let { banner ->
                            Image(
                                painter = rememberImagePainter(data = banner),
                                contentDescription = "App Banner",
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(180.dp)
                            )
                        }
                        Spacer(modifier = Modifier.height(16.dp))
                        Image(
                            painter = rememberImagePainter(data = app.icon),
                            contentDescription = "App Icon",
                            modifier = Modifier.size(64.dp)
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(text = app.name, style = MaterialTheme.typography.headlineMedium)
                        Spacer(modifier = Modifier.height(8.dp))
                        RatingBar(value = app.rating)
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AppDetailScreenPreview() {
    AppDetailScreen(state = DetailState.Loaded(
        AppDetailView(
            id = "1",
            name = "App Name",
            icon = "https://pool.img.aptoide.com/appzonestoreandroid/69b65adc931d895af682d8f921353312_icon.jpg",
            banner = "https://pool.img.aptoide.com/appzonestoreandroid/c9c47d10bb6a5e1188c81f6efe06f1d5_fgraphic.png",
            rating = 5
        )
    ))
}
