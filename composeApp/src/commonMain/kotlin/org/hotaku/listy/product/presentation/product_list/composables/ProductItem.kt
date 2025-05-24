package org.hotaku.listy.product.presentation.product_list.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import kotlinx.datetime.Clock
import org.hotaku.listy.core.presentation.brightBackgroundBlue
import org.hotaku.listy.core.presentation.brightBackgroundGray
import org.hotaku.listy.core.presentation.brightBlue
import org.hotaku.listy.core.presentation.brightGray
import org.hotaku.listy.core.presentation.composables.CheckBox
import org.hotaku.listy.core.presentation.composables.DefaultCard
import org.hotaku.listy.core.presentation.composables.VerticalSpacer_8dp
import org.hotaku.listy.core.presentation.disableGray
import org.hotaku.listy.core.presentation.gray
import org.hotaku.listy.core.presentation.powder
import org.hotaku.listy.core.presentation.primaryBlue
import org.hotaku.listy.product.presentation.UiProduct
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun ProductItem(
    modifier: Modifier = Modifier,
    product: UiProduct,
    onDoneClick: () -> Unit,
) {
    DefaultCard(
        cardColor = if (product.done) brightBackgroundGray else brightBackgroundBlue,
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
                    Text(
                        text = product.name,
                        fontSize = TextUnit(20f, TextUnitType.Sp),
                        color = if (product.done) disableGray else primaryBlue,
                        textDecoration = TextDecoration.LineThrough.takeIf { product.done }
                    )

                    CheckBox(
                        isChecked = product.done,
                        onCheckedChange = { onDoneClick() }
                    )
                }

                Text(
                    text = product.description,
                    color = disableGray,
                    textDecoration = TextDecoration.LineThrough.takeIf { product.done }
                )
            }
        }
    )
}

@Preview
@Composable
fun ProductItemPreview() {
    Column (modifier = Modifier.background(powder).padding(16.dp)) {
        ProductItem(
            product = UiProduct(
                id = 1,
                name = "Sample Product",
                description = "This is a sample product description.",
                categoryId = 1,
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
                done = false,
                dateCreated = Clock.System.now(),
            ),
            onDoneClick = {}
        )
    }
}