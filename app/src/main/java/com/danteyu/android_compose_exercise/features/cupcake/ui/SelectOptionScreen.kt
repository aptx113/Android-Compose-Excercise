package com.danteyu.android_compose_exercise.features.cupcake.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.danteyu.android_compose_exercise.R
import com.danteyu.android_compose_exercise.components.FormattedPriceLabel
import com.danteyu.android_compose_exercise.features.cupcake.ui.theme.CupCakeTheme

@Composable
fun SelectOptionScreen(
    subtotal: String,
    options: List<String>,
    modifier: Modifier = Modifier,
    onSelectionChanged: (String) -> Unit = {},
    onCancelButtonClicked: () -> Unit = {},
    onNextButtonClicked: () -> Unit = {}
) {
    var selectedValue by rememberSaveable {
        mutableStateOf("")
    }

    Column(modifier = modifier.padding(16.dp)) {
        options.forEach { item ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.selectable(
                    selected = selectedValue == item,
                    onClick = {
                        selectedValue = item
                        onSelectionChanged(item)
                    },
                )
            ) {
                RadioButton(selected = selectedValue == item, onClick = {
                    selectedValue = item
                    onSelectionChanged(item)
                })
                Text(text = item)
            }
        }
        Divider(thickness = 1.dp, modifier = modifier.padding(bottom = 16.dp))
        FormattedPriceLabel(
            subtotal = subtotal,
            modifier
                .align(Alignment.End)
                .padding(top = 16.dp, bottom = 16.dp)
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            OutlinedButton(modifier = Modifier.weight(1f), onClick = onCancelButtonClicked) {
                Text(text = stringResource(id = R.string.cancel))
            }
            Button(
                modifier = Modifier.weight(1f),
                enabled = selectedValue.isNotEmpty(),
                onClick = onNextButtonClicked
            ) {
                Text(text = stringResource(id = R.string.next))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SelectOptionPreview() {
    CupCakeTheme {
        SelectOptionScreen(
            subtotal = "299.99",
            options = listOf("Option 1", "Option 2", "Option 3", "Option 4")
        )
    }
}