package org.hotaku.listy.product.presentation.product_list.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import listy.composeapp.generated.resources.Res
import listy.composeapp.generated.resources.danger
import listy.composeapp.generated.resources.empty
import listy.composeapp.generated.resources.empty_state_error_message
import org.hotaku.listy.core.presentation.powder
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.resources.vectorResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun Message(
    modifier: Modifier = Modifier,
    vectorRes: ImageVector = vectorResource(Res.drawable.danger),
    message: String? = null,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Image(
            modifier = Modifier
                .fillMaxWidth(.7f)
                .aspectRatio(1f),
            imageVector = vectorRes,
            contentDescription = null,
        )
        Text(
            modifier = Modifier
                .padding(horizontal = 32.dp),
            text = message ?: stringResource(Res.string.empty_state_error_message),
            fontSize = TextUnit(20f, TextUnitType.Sp),
            textAlign = TextAlign.Center
        )
    }
}

@Preview
@Composable
fun ErrorStatePreview() {
    Box(
        modifier = Modifier.background(powder).fillMaxSize().padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Message(
            modifier = Modifier.fillMaxWidth(),
            vectorRes = vectorResource(Res.drawable.empty),
            message = "Error Text"
        )
    }
}