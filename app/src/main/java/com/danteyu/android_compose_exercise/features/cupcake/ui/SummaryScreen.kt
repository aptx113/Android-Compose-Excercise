package com.danteyu.android_compose_exercise.features.cupcake.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.danteyu.android_compose_exercise.R
import com.danteyu.android_compose_exercise.components.FormattedPriceLabel
import com.danteyu.android_compose_exercise.features.cupcake.model.OrderUiState
import com.danteyu.android_compose_exercise.features.cupcake.ui.theme.CupCakeTheme

@Composable
fun OrderSummaryScreen(
    orderUiState: OrderUiState,
    modifier: Modifier = Modifier,
    onCancelButtonClicked: () -> Unit = {},
    onSendButtonClicked: (String, String) -> Unit = { x, y -> x + y }
) {
    val resources = LocalContext.current.resources
    val numberOfCupcakes = resources.getQuantityString(
        R.plurals.cupcakes,
        orderUiState.quantity,
        orderUiState.quantity
    )
    val orderSummary = stringResource(
        id = R.string.order_details,
        numberOfCupcakes,
        orderUiState.flavor,
        orderUiState.date,
        orderUiState.quantity
    )
    val newOrder = stringResource(id = R.string.new_cupcake_order)
    val items = listOf(
        Pair(stringResource(id = R.string.quantity), numberOfCupcakes),
        Pair(stringResource(id = R.string.flavor), orderUiState.flavor),
        Pair(stringResource(id = R.string.pickup_date), orderUiState.date)
    )
    Column(modifier = modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
        items.forEach { item ->
            Text(text = item.first.uppercase())
            Text(text = item.second, fontWeight = FontWeight.Bold)
            Divider(thickness = 1.dp)
        }
        Spacer(modifier = modifier.height(8.dp))
        FormattedPriceLabel(subtotal = orderUiState.price, modifier = Modifier.align(Alignment.End))
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = { onSendButtonClicked(newOrder, orderSummary) }) {
            Text(text = stringResource(id = R.string.send))
        }
        OutlinedButton(modifier = Modifier.fillMaxWidth(), onClick = onCancelButtonClicked) {
            Text(text = stringResource(id = R.string.cancel))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun OrderSummaryPreview() {
    CupCakeTheme {
        OrderSummaryScreen(
            orderUiState = OrderUiState(0, "Test", "Test", "$300.00"),
        )
    }
}