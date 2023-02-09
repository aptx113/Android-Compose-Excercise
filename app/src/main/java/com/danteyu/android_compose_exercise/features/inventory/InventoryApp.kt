package com.danteyu.android_compose_exercise.features.inventory

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.danteyu.android_compose_exercise.R
import com.danteyu.android_compose_exercise.features.inventory.ui.navigation.InventoryNavHost

@Composable
fun InventoryApp(navHostController: NavHostController = rememberNavController()) {
    InventoryNavHost(navController = navHostController)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InventoryTopAppBar(
    title: String,
    canNavigateBack: Boolean,
    modifier: Modifier = Modifier,
    navigateUp: () -> Unit = {}
) {
    if (canNavigateBack) {
        TopAppBar(
            title = { Text(text = title) },
            modifier = modifier,
            navigationIcon = {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = stringResource(id = R.string.back_button)
                    )
                }
            }
        )
    } else {
        TopAppBar(title = { Text(text = title) }, modifier = modifier)
    }
}
