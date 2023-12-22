package com.test.faurecia.common.ui

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.test.faurecia.R

@Composable
fun RatingBar(
    modifier: Modifier = Modifier,
    value: Int,
    size: Dp = 24.dp,
    padding: Dp = 4.dp,
    activeColor: Color = Color.Yellow,
    inactiveColor: Color = Color.Gray,
    steps: Int = 5
) {
    Row(modifier = modifier) {
        for (i in 1..steps) {
            Icon(
                painter = painterResource(R.drawable.ic_star_filled),
                contentDescription = null,
                tint = if (i <= value) activeColor else inactiveColor,
                modifier = Modifier
                    .size(size)
                    .padding(padding)
            )
        }
    }
}
