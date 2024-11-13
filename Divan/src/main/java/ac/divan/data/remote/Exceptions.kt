package ac.divan.data.remote

import java.io.IOException

class NoInternetException(errorMessage: String) : IOException(errorMessage)
class ConnectionException(errorMessage: String, cause: Throwable?, val statusCode: Int) : IOException(errorMessage, cause)
class UnAuthorizedException(errorMessage: String, val url: String? = null, cause: Throwable?, val statusCode: Int) : IOException(errorMessage, cause)