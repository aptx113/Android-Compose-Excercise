package com.danteyu.android_compose_exercise.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.danteyu.android_compose_exercise.R

@Composable
fun FormattedPriceLabel(subtotal: String, modifier: Modifier = Modifier) {
    Text(text = stringResource(id = R.string.subtotal_price, subtotal), modifier = modifier)
}