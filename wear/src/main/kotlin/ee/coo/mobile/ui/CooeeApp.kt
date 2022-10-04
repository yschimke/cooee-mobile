/*
 * Copyright 2021 Google LLC
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *      http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

@file:OptIn(ExperimentalLifecycleComposeApi::class)

package ee.coo.mobile.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.wear.compose.material.ScalingLazyListState
import com.google.android.horologist.compose.navscaffold.WearNavScaffold
import com.google.android.horologist.compose.navscaffold.scalingLazyColumnComposable
import com.google.android.horologist.networks.ExperimentalHorologistNetworksApi
import ee.coo.mobile.ui.navigation.Screens
import ee.coo.wear.browse.BrowseScreen
import ee.coo.wear.login.LoginScreen

@Composable
fun CooeeApp(
    navController: NavHostController,
    viewModel: AppViewModel = hiltViewModel()
) {
    val appState by viewModel.state.collectAsStateWithLifecycle()

    WearNavScaffold(
        startDestination = Screens.Home.route,
        navController = navController,
    ) {
        scalingLazyColumnComposable(
            route = Screens.Home.route,
            scrollStateBuilder = { ScalingLazyListState(initialCenterItemIndex = 0) }
        ) {
            BrowseScreen(
                modifier = Modifier.fillMaxSize(),
                viewModel = hiltViewModel(),
                focusRequester = it.viewModel.focusRequester,
                scrollState = it.scrollableState,
                navController = navController,
                onAuth = { navController.navigate(Screens.Login.route) }
            )
        }

        scalingLazyColumnComposable(
            route = Screens.Login.route,
            scrollStateBuilder = { ScalingLazyListState(initialCenterItemIndex = 0) }
        ) {
            LoginScreen(
                modifier = Modifier.fillMaxSize(),
                viewModel = hiltViewModel(),
                focusRequester = it.viewModel.focusRequester,
                scrollState = it.scrollableState,
                navController = navController
            )
        }
    }

    LaunchedEffect(appState.account) {
        if (appState.account == null) {
            navController.navigate(Screens.Login.route)
        }
    }
}
