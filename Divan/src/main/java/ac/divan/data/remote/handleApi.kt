package ac.divan.data.remote

import ac.divan.util.Constants
import timber.log.Timber

internal inline fun <T> handleApi(transform: () -> T): NetworkResult<T> {
    return try {
        val data = NetworkResult.Success(transform.invoke())
        Timber.tag("HandleAPI").i(data.toString())
        data
    } catch (e: Exception) {
        Timber.tag("HandleAPI").e(e.toString())
        when (e) {
            is NoInternetException -> NetworkResult.Error(
                Constants.STATUS_CODE_NO_INTERNET,
                e.message.toString()
            )
            is UnAuthorizedException -> NetworkResult.Error(e.statusCode, e.message.toString())
            is ConnectionException -> NetworkResult.Error(e.statusCode, e.message.toString())
            else -> NetworkResult.Error(0, e.message.toString())
        }
    }
}