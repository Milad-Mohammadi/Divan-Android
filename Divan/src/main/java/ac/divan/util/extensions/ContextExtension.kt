package ac.divan.util.extensions

import android.content.Context
import android.net.ConnectivityManager


fun Context.hasInternetConnection(): Boolean {
    val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    return connectivityManager.activeNetworkInfo != null && connectivityManager.activeNetworkInfo!!.isConnected
}