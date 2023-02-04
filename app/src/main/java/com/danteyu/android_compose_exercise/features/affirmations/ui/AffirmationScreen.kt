package com.danteyu.android_compose_exercise.features.affirmations.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.danteyu.android_compose_exercise.R
import com.danteyu.android_compose_exercise.features.affirmations.data.DataSource
import com.danteyu.android_compose_exercise.features.affirmations.model.Affirmation
import com.danteyu.android_compose_exercise.theme.AndroidComposeExerciseTheme

@Composable
fun AffirmationScreen() {
    val context = LocalContext.current
    AndroidComposeExerciseTheme {
        AffirmationList(affirmationList = DataSource().loadAffirmations())
    }
}

@Composable
private fun AffirmationList(affirmationList: List<Affirmation>, modifier: Modifier = Modifier) {
    LazyColumn {
        items(affirmationList) { affirmation ->
            AffirmationCard(affirmation = affirmation)
        }
    }
}

@Composable
private fun AffirmationCard(affirmation: Affirmation, modifier: Modifier = Modifier) {
    ElevatedCard(
        modifier = modifier.padding(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column {
            Image(
                painter = painterResource(id = affirmation.imageResourceId),
                contentDescription = stringResource(affirmation.stringResourceId),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(194.dp),
                contentScale = ContentScale.Crop
            )
            Text(
                text = stringResource(id = affirmation.stringResourceId),
                modifier = Modifier.padding(16.dp),
                style = MaterialTheme.typography.titleLarge
            )
        }
    }
}

@Preview
@Composable
private fun AffirmationCardPreview() {
    AndroidComposeExerciseTheme {
        AffirmationCard(affirmation = Affirmation(R.string.affirmation1, R.drawable.image1))
    }
}