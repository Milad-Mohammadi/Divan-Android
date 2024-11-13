package ac.divan.data.remote.interceptors

import ac.divan.R
import ac.divan.data.remote.ConnectionException
import ac.divan.data.remote.NoInternetException
import ac.divan.data.remote.UnAuthorizedException
import ac.divan.util.extensions.hasInternetConnection
import android.content.Context
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import org.json.JSONObject
import timber.log.Timber


class ErrorResponseInterceptor(private val context: Context): Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        if (context.hasInternetConnection().not()) throw NoInternetException(context.getString(R.string.no_internet_connection))

        val request: Request = chain.request()
        val requestUrl = request.url.toString()
        val response = chain.proceed(request)
        val responseCode = response.code
        if (response.isSuccessful) return response

        val responseBody = response.body?.string().toString()
        Timber.e(responseBody)
        val errorObject = try { JSONObject(responseBody) } catch (e: Exception) { JSONObject() }
        val message = if (errorObject.has("message")) {
            errorObject["message"].toString().also { if (it.isBlank() || it == "null") context.getString(R.string.connection_error_description) }
        } else {
            context.getString(R.string.connection_error_description)
        }

        val errorException = createErrorException(requestUrl, responseCode, message)
        errorException.let { throw it }
    }
}

private fun createErrorException(
    url: String?,
    httpCode: Int,
    message: String,
    cause: Throwable? = null
): Exception {
    val exception: Exception = when (httpCode) {
        401, 403 -> UnAuthorizedException(message, url, cause, httpCode)
        else -> ConnectionException(message, cause, httpCode)
    }
    return exception
}