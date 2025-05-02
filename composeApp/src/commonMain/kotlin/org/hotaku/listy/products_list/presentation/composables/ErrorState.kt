package org.hotaku.listy.products_list.presentation.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import listy.composeapp.generated.resources.Res
import listy.composeapp.generated.resources.empty_state_error_message
import org.jetbrains.compose.resources.stringResource

@Composable
fun ErrorState(
    modifier: Modifier = Modifier,
    message: String? = null,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceAround
    ) {
        Text(
            text = "\uD83D\uDE15",
            fontSize = TextUnit(value = 120f, type = TextUnitType.Sp)
        )
        Text(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 32.dp),
            text = message ?: stringResource(Res.string.empty_state_error_message)
        )
    }
}