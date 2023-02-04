package com.danteyu.android_compose_exercise.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.danteyu.android_compose_exercise.R

@Composable
fun ComposeTaskManagerApp() {
    TaskManagerContent(
        imagePainter = painterResource(id = R.drawable.ic_task_completed),
        title = stringResource(
            id = R.string.all_tasks_completed
        ), content = stringResource(id = R.string.nice_work)
    )
}

@Composable
fun TaskManagerContent(
    imagePainter: Painter,
    title: String,
    content: String,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        Image(painter = imagePainter, contentDescription = null)
        Text(
            text = title,
            fontWeight = FontWeight.Bold,
            modifier = modifier.padding(top = 24.dp, bottom = 8.dp)
        )
        Text(text = content, fontSize = 16.sp)
    }
}

@Preview(showBackground = true)
@Composable
fun ComposeTaskManagerAppPreview() {
    Surface {
        ComposeTaskManagerApp()
    }
}