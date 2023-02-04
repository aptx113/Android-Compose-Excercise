package com.danteyu.android_compose_exercise.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.danteyu.android_compose_exercise.R

@Composable
fun ComposeQuadrant() {
    Column {
        Row(Modifier.weight(1f)) {
            ComposeCard(
                title = stringResource(id = R.string.text_composable),
                content = stringResource(id = R.string.text_content),
                backgroundColor = Color.Green,
                modifier = Modifier.weight(1f)
            )
            ComposeCard(
                title = stringResource(R.string.image_composable),
                content = stringResource(R.string.image_content),
                backgroundColor = Color.Yellow,
                modifier = Modifier.weight(1f)
            )
        }
        Row(Modifier.weight(1f)) {
            ComposeCard(
                title = stringResource(R.string.row_composable),
                content = stringResource(R.string.row_content),
                backgroundColor = Color.Cyan,
                modifier = Modifier.weight(1f)
            )
            ComposeCard(
                title = stringResource(R.string.column_composable),
                content = stringResource(R.string.column_content),
                backgroundColor = Color.LightGray,
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Composable
fun ComposeCard(
    title: String,
    content: String,
    backgroundColor: Color,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .background(backgroundColor)
            .padding(16.dp)
    ) {
        Text(
            text = title,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        Text(text = content, textAlign = TextAlign.Justify)
    }
}

@Preview
@Composable
fun ComposeCardPreview() {
    Surface {
        ComposeQuadrant()
    }
}
