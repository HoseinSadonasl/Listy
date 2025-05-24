package org.hotaku.listy.core.presentation.composables

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import org.hotaku.listy.core.presentation.powder
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun TopRoundedCard(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors().copy(
            containerColor = powder,
        ),
        shape = RoundedCornerShape(
            topStart = 24.dp,
            topEnd = 24.dp,
            bottomStart = 0.dp,
            bottomEnd = 0.dp,
        )
    ) {
        content()
    }
}

@Preview
@Composable
fun TopRoundedCardPreview() {
    TopRoundedCard(
        modifier = Modifier.fillMaxWidth(),
    ) {
        Text(
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            text = "This is a card!",
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun DefaultCard(
    modifier: Modifier = Modifier,
    cardColor: Color = powder,
    content: @Composable () -> Unit
) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors().copy(
            containerColor = cardColor,
        ),
        shape = RoundedCornerShape(24.dp)
    ) {
        content()
    }
}

@Preview
@Composable
fun DefaultCardPreview() {
    DefaultCard(
        modifier = Modifier.fillMaxWidth(),
    ) {
        Text(
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            text = "This is a card!",
            textAlign = TextAlign.Center
        )
    }
}