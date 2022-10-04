package ee.coo.wear.login

import com.google.android.gms.auth.api.signin.GoogleSignInAccount

data class LoginScreenState(
    val account: GoogleSignInAccount? = null
)
