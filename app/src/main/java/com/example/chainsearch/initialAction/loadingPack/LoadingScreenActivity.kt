package com.example.chainsearch.initialAction.loadingPack

import android.content.Intent
import android.os.Build
import com.example.chainsearch.R
import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import com.example.chainsearch.initialAction.viewModels.LoadingScreenViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

private val viewModel: LoadingScreenViewModel = LoadingScreenViewModel()
class LoadingScreenActivity : ComponentActivity() {

    private var keepSplash = true

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_screen)

        val splashScreen = installSplashScreen()
        splashScreen.setKeepOnScreenCondition { keepSplash }

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        lifecycleScope.launch {
            delay(500)
            keepSplash = false

            startActivity(Intent(this@LoadingScreenActivity, LoadingScreenTemplate::class.java))

            withContext(Dispatchers.IO) {
                viewModel.checkExternalInternalData(
                    this@LoadingScreenActivity, 1.5)
                viewModel.setNewVal(true)
            }
        }
    }
}

class LoadingScreenTemplate : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LoadingScreen(viewModel)
        }
    }
}
