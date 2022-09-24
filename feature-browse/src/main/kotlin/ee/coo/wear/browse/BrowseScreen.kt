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

package ee.coo.wear.browse

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.navigation.NavController
import androidx.wear.compose.material.AutoCenteringParams
import androidx.wear.compose.material.ScalingLazyColumn
import androidx.wear.compose.material.ScalingLazyListState
import androidx.wear.compose.material.Text
import com.google.android.horologist.compose.layout.StateUtils.rememberStateWithLifecycle
import com.google.android.horologist.compose.navscaffold.scrollableColumn

@Suppress("UNUSED_PARAMETER")
@Composable
fun BrowseScreen(
    scrollState: ScalingLazyListState,
    focusRequester: FocusRequester,
    modifier: Modifier = Modifier,
    viewModel: BrowseViewModel,
    navController: NavController,
) {
    val state by rememberStateWithLifecycle(viewModel.state)

    BrowseScreen(
        scrollState = scrollState,
        focusRequester = focusRequester,
        modifier = modifier,
        state = state
    )
}

@Composable
fun BrowseScreen(
    scrollState: ScalingLazyListState,
    focusRequester: FocusRequester,
    state: BrowseScreenState,
    modifier: Modifier = Modifier,
) {
    ScalingLazyColumn(
        modifier = modifier
            .scrollableColumn(focusRequester, scrollState),
        state = scrollState,
        autoCentering = AutoCenteringParams(itemIndex = 0)
    ) {
        item {
            Text("Nothing ${state.nothing}")
        }
    }
}
