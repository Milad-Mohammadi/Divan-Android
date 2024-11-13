package ac.divan.data.remote

sealed class NetworkResult<out R> {

    data class Success<out T>(val data: T) : NetworkResult<T>()
    data class Error(val code: Int, val message: String) : NetworkResult<Nothing>()
    object Loading : NetworkResult<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is Error -> "Error[code=$code][message=$message]"
            Loading -> "Loading"
        }
    }

    fun getValue(): R? {
        return when (this) {
            is Success<R> -> data
            is Error -> null
            is Loading -> null
        }
    }
}

/**
 * `true` if [NetworkResult] is of type [Success] & holds non-null [Success.data].
 */
val NetworkResult<*>.succeeded
    get() = this is NetworkResult.Success

fun <T> NetworkResult<T>.successOr(fallback: T): T {
    return (this as? NetworkResult.Success<T>)?.data ?: fallback
}

inline fun <T, R> NetworkResult<T>.map(transform: (T) -> R): NetworkResult<R> =
    when (this) {
        is NetworkResult.Success -> NetworkResult.Success(transform(data))
        is NetworkResult.Error -> NetworkResult.Error(this.code, this.message)
        is NetworkResult.Loading -> NetworkResult.Loading
    }

inline fun <T> NetworkResult<T>.onSuccess(action: (T) -> Unit): NetworkResult<T> {
    if (this is NetworkResult.Success) action(data)
    return this
}

inline fun <T> NetworkResult<T>.onError(action: (Int, String) -> Unit): NetworkResult<T> {
    if (this is NetworkResult.Error) action(code, message)
    return this
}

inline fun <R, T : R> NetworkResult<T>.getOrElse(onError: (code: Int, message: String) -> R): R {
    return when (this) {
        is NetworkResult.Success -> data
        is NetworkResult.Error -> onError(code, message)
        is NetworkResult.Loading -> error("not support loading type")
    }
}

fun <R, T : R> NetworkResult<T>.get(): R {
    return when (this) {
        is NetworkResult.Success -> data
        is NetworkResult.Error -> error("")
        is NetworkResult.Loading -> error("not support loading type")
    }
}
