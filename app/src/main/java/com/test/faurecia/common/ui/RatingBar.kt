package com.test.faurecia.common.ui

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.test.faurecia.R

@Composable
fun RatingBar(
    value: Int,
    steps: Int = 5
) {
    Row {
        for (i in 1..steps) {
            Icon(
                painter = painterResource(R.drawable.ic_star_filled),
                contentDescription = null,
                tint = if (i <= value) Color.Yellow else Color.Gray,
                modifier = Modifier
                    .size(24.dp)
                    .padding(4.dp)
            )
        }
    }
}
