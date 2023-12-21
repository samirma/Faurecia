package com.test.faurecia.feature.list

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberImagePainter
import com.test.faurecia.feature.list.model.ListState


@Composable
fun AppListScreen() {
    val viewModel: ListViewModel = hiltViewModel()

    LaunchedEffect(viewModel) {
        viewModel.fetchAppsList()
    }

    val state by viewModel.state.collectAsState()

    when (state) {
        is ListState.Loading -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }

        is ListState.Error -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(text = "An error occurred", style = MaterialTheme.typography.headlineLarge)
            }
        }

        is ListState.ListViewModelState -> {
            val appList = (state as ListState.ListViewModelState).appList
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(appList) { app ->
                    Card(
                        modifier = Modifier.padding(8.dp),
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
            }
        }
    }
}