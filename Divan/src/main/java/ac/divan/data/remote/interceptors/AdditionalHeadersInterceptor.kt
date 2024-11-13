package ac.divan.data.remote.interceptors

import android.content.Context
import okhttp3.Interceptor
import okhttp3.Response

class AdditionalHeadersInterceptor(private val context: Context): Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request()
            .newBuilder()
            .addHeader("Accept", "application/json")
            .addHeader("Cache-Control", "no-cache")
            .build()

        return chain.proceed(requestBuilder)
    }
}