package ac.divan.util.extensions

import ac.divan.ui.theme.Primary
import android.content.Context
import android.net.ConnectivityManager
import android.net.Uri
import androidx.browser.customtabs.CustomTabsIntent
import androidx.compose.ui.graphics.toArgb


fun Context.hasInternetConnection(): Boolean {
    val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    return connectivityManager.activeNetworkInfo != null && connectivityManager.activeNetworkInfo!!.isConnected
}

fun Context.openUrl(url: String) {

    val builder = CustomTabsIntent.Builder()
    builder.setShowTitle(true)
    builder.setInstantAppsEnabled(true)
    builder.setToolbarColor(Primary.toArgb())

    val customBuilder = builder.build()

    customBuilder.launchUrl(this, Uri.parse(url))
}