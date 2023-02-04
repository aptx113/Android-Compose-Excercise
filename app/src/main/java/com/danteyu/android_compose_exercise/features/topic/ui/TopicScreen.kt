package com.danteyu.android_compose_exercise.features.topic.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.danteyu.android_compose_exercise.R
import com.danteyu.android_compose_exercise.features.topic.data.TopicDataSource
import com.danteyu.android_compose_exercise.features.topic.model.Topic
import com.danteyu.android_compose_exercise.theme.AndroidComposeExerciseTheme

@Composable
fun TopicScreen() {
    AndroidComposeExerciseTheme {
        TopicGrid(topics = TopicDataSource.topics)
    }
}

@Composable
fun TopicGrid(topics: List<Topic>, modifier: Modifier = Modifier) {
    LazyVerticalGrid(columns = GridCells.Fixed(2), contentPadding = PaddingValues(4.dp)) {
        items(topics) { topic ->
            TopicCard(topic = topic)
        }
    }
}

@Composable
private fun TopicCard(topic: Topic, modifier: Modifier = Modifier) {
    ElevatedCard(elevation = CardDefaults.cardElevation(4.dp), modifier = modifier.padding(4.dp)) {
        Row {
            Image(
                painter = painterResource(id = topic.drawableResource),
                contentDescription = stringResource(
                    id = topic.stringResource
                ),
                modifier = Modifier
                    .size(68.dp)
                    .aspectRatio(1f),
                contentScale = ContentScale.Crop
            )
            Column(
                modifier = modifier.padding(
                    start = 16.dp,
                    top = 16.dp,
                    end = 16.dp,
                )
            ) {
                Text(
                    text = stringResource(id = topic.stringResource),
                    style = MaterialTheme.typography.bodyMedium
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = modifier.padding(top = 8.dp)
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_grain),
                        contentDescription = null,
                        modifier = Modifier
                            .padding(start = 16.dp)
                            .size(12.dp)
                    )
                    Text(
                        text = topic.availableCourses.toString(),
                        modifier = modifier.padding(start = 8.dp),
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun TopicScreenPreview() {
    TopicScreen()
}

@Preview
@Composable
fun TopicCardPreview() {
    AndroidComposeExerciseTheme {
        TopicCard(topic = TopicDataSource.topics[1])
    }
}
