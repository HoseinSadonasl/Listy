package org.hotaku.listy.core.presentation.composables

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.hotaku.listy.core.presentation.powder
import org.hotaku.listy.core.presentation.primaryBlue
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun CheckBox(
    modifier: Modifier = Modifier,
    isChecked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
) {
    Checkbox(
        modifier = modifier,
        checked = isChecked,
        onCheckedChange = onCheckedChange,
        colors = CheckboxDefaults.colors(
            checkedColor = primaryBlue,
            uncheckedColor = primaryBlue.copy(alpha = .5f),
        ),
    )
}

@Preview
@Composable
fun CheckBoxPreview() {
    Surface(
        modifier = Modifier.padding(24.dp),
        color = powder,
    ) {
        CheckBox(
            isChecked = false,
            onCheckedChange = {}
        )
    }

}