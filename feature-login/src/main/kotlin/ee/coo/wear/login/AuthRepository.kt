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

package ee.coo.wear.login

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.baulsupp.cooee.p.GoogleTokenExchangeRequest
import com.baulsupp.cooee.p.GoogleTokenExchangeResponse
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.auth.api.signin.GoogleSignInStatusCodes
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.common.api.Scope
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import ee.coo.wear.api.CooeeApiRepository
import ee.coo.wear.api.TokenAccess
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AuthRepository @Inject constructor(
    @ApplicationContext applicationContext: Context,
    val apiRepository: CooeeApiRepository
): TokenAccess {
    val serverClientId = "415869238805-8ktkatm0d5gfbbkhfu8vv4k3quf4hfbi.apps.googleusercontent.com"
    val googleApiClient =
        GoogleSignIn.getClient(
            applicationContext,
            GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .requestId()
                .requestProfile()
                .requestIdToken(serverClientId)
                .requestServerAuthCode(serverClientId)
                .requestScopes(Scope("https://www.googleapis.com/auth/drive.file"))
                .build()
        )

    val state = MutableStateFlow<GoogleSignInAccount?>(null)

    init {
        state.update {
            GoogleSignIn.getLastSignedInAccount(applicationContext)
        }

//        if (state.value.account == null) {
//            viewModelScope.launch {
//                try {
//                    val account: GoogleSignInAccount = googleApiClient.silentSignIn().await()
//
//                    state.update {
//                        it.copy(account = account)
//                    }
//                } catch (e: ApiException) {
//                    val reason = GoogleSignInStatusCodes.getStatusCodeString(e.statusCode)
//                }
//            }
//        }
    }

    suspend fun updateAccount(account: GoogleSignInAccount?) {
        state.value = account
        if (account != null) {
            val idToken = account.idToken
            if (idToken != null) {
                apiRepository.requestResponse<GoogleTokenExchangeRequest, GoogleTokenExchangeResponse>(
                    "googleTokenExchange",
                    GoogleTokenExchangeRequest(
                        idToken = idToken,
                        authCode = account.serverAuthCode
                    )
                )
            }
        }
    }

    suspend fun logout() {
        googleApiClient.signOut().await()
        updateAccount(null)
    }

    override val idToken: String? = state.value?.idToken
}
