package org.hotaku.listy.product.presentation.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun LoadingState(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
                .horizontalScroll(rememberScrollState())
        ) {
            repeat(times = 6) {
                Box(
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxHeight()
                        .width(64.dp)
                        .clip(RoundedCornerShape(percent = 50))
                        .background(Color.LightGray)
                )
            }
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .verticalScroll(rememberScrollState())
        ) {
            repeat(times = 10) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(72.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(64.dp)
                            .padding(16.dp)
                            .clip(RoundedCornerShape(percent = 50))
                            .background(Color.LightGray)
                    )
                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxHeight(),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f)
                                .clip(RoundedCornerShape(percent = 50))
                                .background(Color.LightGray)
                        )
                        Box(
                            modifier = Modifier
                                .fillMaxWidth(.6f)
                                .weight(1f)
                                .clip(RoundedCornerShape(percent = 50))
                                .background(Color.LightGray)
                        )
                    }
                    Box(
                        modifier = Modifier
                            .size(64.dp)
                            .padding(16.dp)
                            .clip(RoundedCornerShape(percent = 50))
                            .background(Color.LightGray)
                    )

                }
            }
        }

    }
}