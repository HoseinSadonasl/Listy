package org.hotaku.listy.product.presentation.product_list.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import kotlinx.datetime.Clock
import org.hotaku.listy.core.presentation.background
import org.hotaku.listy.core.presentation.cardBackgroundGray
import org.hotaku.listy.core.presentation.cardBrightBackgroundGray
import org.hotaku.listy.core.presentation.composables.CaptionText
import org.hotaku.listy.core.presentation.composables.CheckBox
import org.hotaku.listy.core.presentation.composables.DefaultCard
import org.hotaku.listy.core.presentation.composables.TitleText
import org.hotaku.listy.core.presentation.composables.VerticalSpacer_8dp
import org.hotaku.listy.core.presentation.disableGray
import org.hotaku.listy.core.presentation.grayTextUnFocused
import org.hotaku.listy.core.presentation.primaryBlue
import org.hotaku.listy.product.presentation.UiProduct
import org.hotaku.listy.product.presentation.productImportance
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun ProductItem(
    modifier: Modifier = Modifier,
    product: UiProduct,
    onDoneClick: () -> Unit,
) {
    DefaultCard(
        cardColor = if (product.done) cardBrightBackgroundGray else cardBackgroundGray,
        content = {
            Column(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(12.dp),
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    TitleText(
                        text = product.name,
                        color = if (product.done) disableGray else primaryBlue,
                        textDecoration = TextDecoration.LineThrough.takeIf { product.done }
                    )

                    CheckBox(
                        enabled = !product.done,
                        isChecked = product.done,
                        onCheckedChange = { onDoneClick() }
                    )
                }
                if (product.description.isNotEmpty()) {
                    CaptionText(
                        text = product.description,
                        color = if (product.done) disableGray else grayTextUnFocused,
                        textDecoration = TextDecoration.LineThrough.takeIf { product.done }
                    )
                }
            }
        }
    )
}

@Preview
@Composable
fun ProductItemPreview() {
    Column(modifier = Modifier.background(background).padding(16.dp)) {
        ProductItem(
            product = UiProduct(
                id = 1,
                name = "Sample Product",
                description = "This is a sample product description.",
                categoryId = 1,
                importance = productImportance.first(),
                done = true,
                dateCreated = Clock.System.now(),
            ),
            onDoneClick = {}
        )
        VerticalSpacer_8dp()
        ProductItem(
            product = UiProduct(
                id = 1,
                name = "Sample Product",
                description = "This is a sample product description.",
                categoryId = 1,
                importance = productImportance.first(),
                done = false,
                dateCreated = Clock.System.now(),
            ),
            onDoneClick = {}
        )
        VerticalSpacer_8dp()
        ProductItem(
            product = UiProduct(
                id = 1,
                name = "Sample Product",
                description = "",
                categoryId = 1,
                importance = productImportance.first(),
                done = false,
                dateCreated = Clock.System.now(),
            ),
            onDoneClick = {}
        )
    }
}