/*
 * Copyright 2021 Google Inc. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package ee.coo.wear.compose.theme

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.wear.compose.material.Colors
import androidx.wear.compose.material.MaterialTheme
import java.time.Instant
import java.time.ZoneOffset
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.time.temporal.ChronoUnit

val CooeeColorPalette = Colors(
    primary = Color(0xff0060bfL), // #0060bf
    primaryVariant = Color(0xff668888L), // #e4eef8
    onPrimary = Color(0xFFffffffL),
    secondary = Color(0xfffffac0L), // #fffac0
    secondaryVariant = Color(0xff240047L),
    onSecondary = Color(0xFF0060bfL),
    onSurfaceVariant = Color(0xff0060bfL),
    onSurface = Color.White
)

@Composable
fun CooeeTheme(
    content: @Composable() () -> Unit
) {
    MaterialTheme(
        colors = CooeeColorPalette,
        content = content
    )
}