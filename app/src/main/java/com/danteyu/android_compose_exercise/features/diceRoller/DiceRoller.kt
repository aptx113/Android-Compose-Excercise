package com.danteyu.android_compose_exercise.features.diceRoller

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.danteyu.android_compose_exercise.R

@Preview
@Composable
fun DiceRollerApp() {
    DiceWithButtonAndImage(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
    )
}

@Composable
fun DiceWithButtonAndImage(modifier: Modifier = Modifier) {
    var result by remember {
        mutableStateOf(1)
    }
    val imageRes = when (result) {
        1 -> R.drawable.dice_1
        2 -> R.drawable.dice_2
        3 -> R.drawable.dice_3
        4 -> R.drawable.dice_4
        5 -> R.drawable.dice_5
        else -> R.drawable.dice_6
    }
    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        Image(painter = painterResource(id = imageRes), contentDescription = result.toString())
        Spacer(modifier = Modifier.height(1.dp))
        Button(onClick = { result = (1..6).random() }) {
            Text(text = stringResource(id = R.string.roll))
        }
    }
}