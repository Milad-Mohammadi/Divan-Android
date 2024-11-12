package ac.divan.ui

import ac.divan.ui.theme.DivanTheme
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private var splashScreenStays = true
    private val delayTime = 100L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen().setKeepOnScreenCondition { splashScreenStays }
        Handler(Looper.getMainLooper()).postDelayed({ splashScreenStays = false }, delayTime)

        setContent {
            DivanTheme {
                MainScreen()
            }
        }
    }
}