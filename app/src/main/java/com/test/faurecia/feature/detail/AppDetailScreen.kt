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
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.test.faurecia.R
import com.test.faurecia.common.ui.ErrorMessage
import com.test.faurecia.common.ui.LoadingIndicator
import com.test.faurecia.common.ui.RatingBar
import com.test.faurecia.feature.detail.model.AppDetailView
import com.test.faurecia.feature.detail.model.DetailState

@Composable
fun AppDetailNavigation(
    navController: NavController,
    appId: String,
    viewModel: AppDetailViewModel = hiltViewModel()
) {

    viewModel.fetchApp(appId)

    val app by viewModel.state.collectAsState()

    AppDetailScreen(app) {
        navController.popBackStack()
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppDetailScreen(state: DetailState, onBack: () -> Unit) {
    val showDialog = remember { mutableStateOf(false) }  // State for the dialog

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "App Detail") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { innerPadding ->
        val modifier = Modifier.padding(innerPadding)

        Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.TopCenter) {
            when (state) {
                is DetailState.Loading -> {
                    LoadingIndicator()
                }

                is DetailState.Error -> {
                    ErrorMessage(stringResource(R.string.an_error_occurred))
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
                        Spacer(modifier = Modifier.height(16.dp))
                        Button(onClick = {
                            showDialog.value = true
                        }) {  // Button is enabled only when state is Loaded
                            Text(stringResource(R.string.download))
                        }
                    }
                }
            }

            if (showDialog.value) {
                AlertDialog(
                    onDismissRequest = { showDialog.value = false },
                    title = { Text(stringResource(R.string.warning)) },
                    text = { Text(stringResource(R.string.download_is_not_available)) },
                    confirmButton = {
                        Button(onClick = { showDialog.value = false }) {
                            Text(stringResource(R.string.ok))
                        }
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AppDetailScreenPreview() {
    AppDetailScreen(
        state = DetailState.Loaded(
            AppDetailView(
                id = "1",
                name = "App Name",
                icon = "https://pool.img.aptoide.com/appzonestoreandroid/69b65adc931d895af682d8f921353312_icon.jpg",
                banner = "https://pool.img.aptoide.com/appzonestoreandroid/c9c47d10bb6a5e1188c81f6efe06f1d5_fgraphic.png",
                rating = 5
            )
        )
    ) {}
}
