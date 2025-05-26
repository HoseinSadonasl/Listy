package org.hotaku.listy.core.presentation.composables

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.hotaku.listy.core.presentation.background
import org.hotaku.listy.core.presentation.brightGray
import org.hotaku.listy.core.presentation.disableGray
import org.hotaku.listy.core.presentation.primaryBlue
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun CheckBox(
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    isChecked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
) {
    Checkbox(
        modifier = modifier,
        checked = isChecked,
        enabled = enabled,
        onCheckedChange = onCheckedChange,
        colors = CheckboxDefaults.colors(
            checkedColor = primaryBlue,
            uncheckedColor = primaryBlue.copy(alpha = .5f),
            checkmarkColor =background,
            disabledCheckedColor = disableGray,
        ),
    )
}

@Preview
@Composable
private fun CheckBoxPreview() {
    Surface(
        modifier = Modifier.padding(24.dp),
        color = background,
    ) {
        CheckBox(
            isChecked = false,
            onCheckedChange = {}
        )
    }

}