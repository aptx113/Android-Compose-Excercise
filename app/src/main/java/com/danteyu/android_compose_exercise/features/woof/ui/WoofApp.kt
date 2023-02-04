package com.danteyu.android_compose_exercise.features.woof.ui

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.danteyu.android_compose_exercise.R
import com.danteyu.android_compose_exercise.features.woof.model.Dog
import com.danteyu.android_compose_exercise.features.woof.model.dogs
import com.danteyu.android_compose_exercise.theme.AndroidComposeExerciseTheme
import com.danteyu.android_compose_exercise.theme.Green50
import com.danteyu.android_compose_exercise.theme.WoofTheme

@ExperimentalMaterial3Api
@Composable
fun WoofApp() {
    WoofTheme {
        Scaffold(
            topBar = { WoofTopAppBar() },
        ) { paddingValue ->
            LazyColumn(
                modifier = Modifier.background(MaterialTheme.colorScheme.surface),
                contentPadding = paddingValue
            ) {
                items(dogs) {
                    DogItem(dog = it)
                }
            }
        }

    }
}

@Composable
private fun WoofTopAppBar(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(color = MaterialTheme.colorScheme.primary),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            modifier = Modifier
                .size(64.dp)
                .padding(8.dp),
            painter = painterResource(id = R.drawable.ic_woof_logo),
            contentDescription = null
        )
        Text(
            text = stringResource(id = R.string.app_name),
            style = MaterialTheme.typography.displayLarge
        )
    }
}

@Composable
private fun DogItem(dog: Dog, modifier: Modifier = Modifier) {
    var expended by remember {
        mutableStateOf(false)
    }
    val color by animateColorAsState(targetValue = if (expended) Green50 else MaterialTheme.colorScheme.surface)
    Card(
        modifier = modifier.padding(8.dp),
        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.background),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier
                .animateContentSize(
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioMediumBouncy,
                        stiffness = Spring.StiffnessLow
                    )
                )
                .background(color = color)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                DogPhoto(dogImage = dog.imageResourceId)
                DogInformation(dogName = dog.name, dogAge = dog.age)
                Spacer(modifier = Modifier.weight(1f))
                DogItemButton(
                    expanded = expended, onClick = { expended = !expended },
                )
            }
            if (expended) {
                DogHobby(dogHobby = dog.hobbies)
            }
        }
    }
}

@Composable
private fun DogItemButton(expanded: Boolean, onClick: () -> Unit, modifier: Modifier = Modifier) {
    IconButton(onClick = onClick) {
        Icon(
            imageVector = if (expanded) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
            tint = MaterialTheme.colorScheme.secondary,
            contentDescription = stringResource(id = R.string.expand_button_content_description)
        )
    }
}

@Composable
private fun DogPhoto(@DrawableRes dogImage: Int, modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(id = dogImage),
        contentDescription = null,
        modifier = modifier
            .size(64.dp)
            .padding(8.dp)
            .clip(RoundedCornerShape(50)),
        contentScale = ContentScale.Crop
    )
}

@Composable
private fun DogInformation(@StringRes dogName: Int, dogAge: Int, modifier: Modifier = Modifier) {
    Column {
        Text(
            text = stringResource(id = dogName),
            style = MaterialTheme.typography.displayMedium,
            modifier = modifier.padding(top = 8.dp)
        )
        Text(
            text = stringResource(id = R.string.years_old, dogAge),
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

@Composable
private fun DogHobby(@StringRes dogHobby: Int, modifier: Modifier = Modifier) {
    Column(modifier = modifier.padding(start = 16.dp, top = 8.dp, bottom = 16.dp, end = 16.dp)) {
        Text(
            text = stringResource(id = R.string.about),
            style = MaterialTheme.typography.displaySmall
        )
        Text(text = stringResource(id = dogHobby), style = MaterialTheme.typography.bodyLarge)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
private fun WoofPreview() {
    WoofTheme(darkTheme = false) {
        WoofApp()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
private fun WoofDarkThemePreview() {
    WoofTheme(darkTheme = true) {
        WoofApp()
    }
}
