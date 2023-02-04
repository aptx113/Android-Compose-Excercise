package com.danteyu.android_compose_exercise.features.cupcake.ui

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.danteyu.android_compose_exercise.R
import com.danteyu.android_compose_exercise.features.cupcake.data.CupCakeDataSource
import com.danteyu.android_compose_exercise.features.cupcake.ui.theme.CupCakeTheme

enum class CupcakeApp(@StringRes val title: Int) {
    Start(title = R.string.cupcake),
    Flavor(title = R.string.choose_flavor),
    Pickup(title = R.string.choose_pickup_date),
    Summary(title = R.string.order_summary)
}

@ExperimentalMaterial3Api
@Composable
fun CupcakeAppBar(
    currentScreen: CupcakeApp,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = {
            Text(
                text = stringResource(id = currentScreen.title),
                color = MaterialTheme.colorScheme.onPrimary
            )
        },
        colors = TopAppBarDefaults.smallTopAppBarColors(MaterialTheme.colorScheme.primary),
        modifier = modifier,
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(
                    onClick = navigateUp,
                ) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = stringResource(id = R.string.back_button),
                        tint = MaterialTheme.colorScheme.onPrimary
                    )
                }
            }
        }
    )
}

@ExperimentalMaterial3Api
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun CupcakeApp(
    modifier: Modifier = Modifier,
    viewModel: OrderViewModel = viewModel(),
    navController: NavHostController = rememberNavController(
    )
) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen =
        CupcakeApp.valueOf(backStackEntry?.destination?.route ?: CupcakeApp.Start.name)
    CupCakeTheme {
        Scaffold(topBar = {
            CupcakeAppBar(
                currentScreen = currentScreen,
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = { navController.navigateUp() })
        }) { innerPadding ->
            val uiState by viewModel.uiState.collectAsState()
            NavHost(
                navController = navController,
                startDestination = CupcakeApp.Start.name,
                modifier = modifier.padding(innerPadding)
            ) {
                composable(route = CupcakeApp.Start.name) {
                    StartOrderScreen(
                        quantityOptions = CupCakeDataSource.quantityOptions,
                        onNextButtonClicked = {
                            viewModel.setQuantity(it)
                            navController.navigate(CupcakeApp.Flavor.name)
                        },
                    )
                }
                composable(route = CupcakeApp.Flavor.name) {
                    val context = LocalContext.current
                    SelectOptionScreen(
                        subtotal = uiState.price,
                        options = CupCakeDataSource.flavors.map { id -> context.getString(id) },
                        onSelectionChanged = { viewModel.setFlavor(it) },
                        onNextButtonClicked = { navController.navigate(CupcakeApp.Pickup.name) },
                        onCancelButtonClicked = {
                            cancelOrderAndNavigateToStart(
                                viewModel,
                                navController
                            )
                        },
                    )
                }
                composable(route = CupcakeApp.Pickup.name) {
                    SelectOptionScreen(
                        subtotal = uiState.price,
                        options = uiState.pickupOptions,
                        onSelectionChanged = { viewModel.setDate(it) },
                        onNextButtonClicked = { navController.navigate(CupcakeApp.Summary.name) },
                        onCancelButtonClicked = {
                            cancelOrderAndNavigateToStart(
                                viewModel,
                                navController
                            )
                        },
                    )
                }
                composable(route = CupcakeApp.Summary.name) {
                    val context = LocalContext.current
                    OrderSummaryScreen(
                        orderUiState = uiState,
                        onCancelButtonClicked = {
                            cancelOrderAndNavigateToStart(
                                viewModel,
                                navController
                            )
                        },
                        onSendButtonClicked = { subject: String, summary: String ->
                            shareOrder(
                                context,
                                subject,
                                summary
                            )
                        }
                    )
                }
            }
        }
    }
}

private fun cancelOrderAndNavigateToStart(
    viewModel: OrderViewModel,
    navHostController: NavHostController
) {
    viewModel.resetOrder()
    navHostController.popBackStack(CupcakeApp.Start.name, inclusive = false)
}

private fun shareOrder(context: Context, subject: String, summary: String) {
    val intent = Intent(Intent.ACTION_SEND).apply {
        type = "text/plain"
        putExtra(Intent.EXTRA_SUBJECT, subject)
        putExtra(Intent.EXTRA_TEXT, summary)
    }
    context.startActivity(
        Intent.createChooser(
            intent,
            context.getString(R.string.new_cupcake_order)
        )
    )
}