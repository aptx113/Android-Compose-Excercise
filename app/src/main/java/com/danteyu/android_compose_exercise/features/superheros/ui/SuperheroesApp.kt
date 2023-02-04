package com.danteyu.android_compose_exercise.features.superheros.ui

import androidx.compose.animation.*
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.Spring.DampingRatioLowBouncy
import androidx.compose.animation.core.Spring.StiffnessLow
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.danteyu.android_compose_exercise.R
import com.danteyu.android_compose_exercise.features.superheros.data.HeroesDataSource
import com.danteyu.android_compose_exercise.features.superheros.model.Hero
import com.danteyu.android_compose_exercise.theme.SuperheroesTheme

@ExperimentalAnimationApi
@ExperimentalMaterial3Api
@Composable
fun HeroesApp() {
    SuperheroesTheme {
        Scaffold(
            topBar = { HeroesTopAppBar() },
            modifier = Modifier.padding(16.dp)
        ) { paddingValues ->
            HeroesList(heroes = HeroesDataSource.heroes, contentPaddingValues = paddingValues)
        }
    }
}

@Composable
private fun HeroesTopAppBar(modifier: Modifier = Modifier) {
    Row(
        verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center,
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp)
            .background(MaterialTheme.colorScheme.background)
    ) {
        Text(
            text = stringResource(id = R.string.super_heroes),
            style = MaterialTheme.typography.displayLarge
        )
    }
}

@ExperimentalAnimationApi
@Composable
private fun HeroesList(
    heroes: List<Hero>,
    contentPaddingValues: PaddingValues,
    modifier: Modifier = Modifier
) {
    val visibleState = remember {
        MutableTransitionState(false).apply {
            targetState = true
        }
    }
    AnimatedVisibility(
        visibleState = visibleState,
        enter = fadeIn(animationSpec = spring(dampingRatio = DampingRatioLowBouncy)),
        exit = fadeOut()
    ) {
        LazyColumn(
            contentPadding = contentPaddingValues,
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = modifier.background(MaterialTheme.colorScheme.background)
        ) {
            itemsIndexed(heroes) { index, hero ->
                HeroItem(
                    hero = hero,
                    modifier = modifier.animateEnterExit(
                        enter = slideInVertically(
                            animationSpec = spring(
                                stiffness = StiffnessLow,
                                dampingRatio = DampingRatioLowBouncy
                            ),
                            initialOffsetY = { it * (index + 1) }
                        )
                    )
                )
            }
        }
    }
}

@Composable
private fun HeroItem(hero: Hero, modifier: Modifier = Modifier) {
    Card(elevation = CardDefaults.cardElevation(2.dp), shape = MaterialTheme.shapes.large) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(16.dp)
                .sizeIn(minHeight = 72.dp)
        ) {
            Column(modifier = modifier.weight(1f)) {
                Text(
                    text = stringResource(id = hero.nameRes),
                    style = MaterialTheme.typography.displaySmall
                )
                Text(
                    text = stringResource(id = hero.descriptionRes),
                    style = MaterialTheme.typography.bodyLarge,
                )
            }
            Spacer(modifier = modifier.width(16.dp))
            Box(
                modifier = Modifier
                    .size(72.dp)
                    .clip(MaterialTheme.shapes.small)
            ) {
                Image(
                    painter = painterResource(id = hero.imageRes),
                    contentDescription = stringResource(id = hero.nameRes),
                    alignment = Alignment.TopCenter,
                    contentScale = ContentScale.FillWidth,
                )
            }
        }
    }
}

@Preview
@Composable
fun HeroesTopAppBarPreview() {
    SuperheroesTheme {
        HeroesTopAppBar()
    }
}

@ExperimentalAnimationApi
@Preview
@Composable
private fun HeroesListPreview() {
    SuperheroesTheme {
        HeroesList(heroes = HeroesDataSource.heroes, PaddingValues(16.dp))
    }
}

@Preview
@Composable
private fun HeroItemPreview() {
    SuperheroesTheme {
        HeroItem(hero = HeroesDataSource.heroes[1])
    }
}
