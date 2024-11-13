package ac.divan.data.remote.interceptors

import ac.divan.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response


class AuthorizationInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val token = BuildConfig.API_KEY
        val requestBuilder = chain.request().newBuilder()
        if (token.isNotBlank()) requestBuilder.addHeader("x-api-key", token)

        return chain.proceed(requestBuilder.build())
    }
}