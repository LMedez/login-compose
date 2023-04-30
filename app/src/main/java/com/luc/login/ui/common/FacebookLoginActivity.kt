package com.luc.login.ui.common

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult

class FacebookLoginActivity : ComponentActivity() {

    private val callbackManager: CallbackManager by lazy {
        CallbackManager.Factory.create()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupLogin()
        doLogin()

    }

    private fun doLogin() {
        LoginManager.getInstance().logInWithReadPermissions(
            this,
            listOf("user_photos", "email", "public_profile", "AccessToken")
        )
    }

    private fun setupLogin() {
        LoginManager.getInstance()
            .registerCallback(callbackManager, object : FacebookCallback<LoginResult> {
                override fun onCancel() {
                    Log.d("TAG++", "facebook login cancelled")
                    setResult(RESULT_CANCELED)
                    finish()
                }

                override fun onError(error: FacebookException) {
                    Log.d("TAG++", "facebook login error")
                    setResult(RESULT_ERROR)
                    finish()
                }

                override fun onSuccess(result: LoginResult) {
                    Log.d("TAG++", "facebook login success")
                    val intent = Intent().apply {
                        putExtra(EXTRA_DATA_FB, result.accessToken)
                    }
                    setResult(RESULT_OK, intent)
                    finish()
                }
            })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        callbackManager.onActivityResult(requestCode, resultCode, data)
        super.onActivityResult(requestCode, resultCode, data)
    }

    companion object {

        const val RESULT_ERROR = 102
        const val EXTRA_DATA_FB = "extraDataFb"

        fun getInstance(context: Context): Intent {
            return Intent(context, FacebookLoginActivity::class.java)
        }
    }
}