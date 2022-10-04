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


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.baulsupp.cooee.p.CommandRequest
import com.baulsupp.cooee.p.CommandResponse
import com.google.api.services.drive.Drive
import dagger.hilt.android.lifecycle.HiltViewModel
import ee.coo.wear.api.CooeeApiRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BrowseViewModel @Inject constructor(
    val cooeeApiRepository: CooeeApiRepository,
) : ViewModel() {
    val state = MutableStateFlow(BrowseScreenState())

    fun makeRequest() {
        viewModelScope.launch {
            cooeeApiRepository.requestStream<CommandRequest, CommandResponse>(
                "runCommand",
                CommandRequest(parsed_command = listOf("hello"))
            )
        }
    }

    fun makeGoogleRequest() {
        viewModelScope.launch {
        }
    }

    fun validate() {
        viewModelScope.launch {
            cooeeApiRepository.validate()
        }
    }

    fun logout() {
        viewModelScope.launch {
            cooeeApiRepository.validate()
        }
    }
}
