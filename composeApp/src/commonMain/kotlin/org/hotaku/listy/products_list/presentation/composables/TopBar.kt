package org.hotaku.listy.products_list.presentation.composables

import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.hotaku.listy.core.presentation.brightGreen
import org.hotaku.listy.core.presentation.composables.IconButton
import org.hotaku.listy.core.presentation.powder

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    title: String,
    onNavigationClick: (() -> Unit)? = null,
) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge,
            )
        },
        navigationIcon = {
            onNavigationClick?.let {
                IconButton(
                    onClick = it,
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors().copy(
            containerColor = brightGreen,
            navigationIconContentColor = powder,
            actionIconContentColor = powder,
            titleContentColor = powder
        )
    )
}