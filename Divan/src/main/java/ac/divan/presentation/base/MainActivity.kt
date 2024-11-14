package ac.divan.presentation.base

import ac.divan.ui.theme.DivanTheme
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen().setKeepOnScreenCondition { viewModel.isLoading.value && viewModel.defaultMenu.value == null }

        setContent {
            DivanTheme {
                val defaultMenu by viewModel.defaultMenu.collectAsState()
                defaultMenu?.let { MainScreen(it) }
            }
        }
    }
}