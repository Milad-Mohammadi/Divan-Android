package ac.divan.data.remote.dto

import ac.divan.util.Constants.STATUS_CODE_OK
import com.google.gson.annotations.SerializedName

data class ApiResponse<T>(
    @SerializedName("data") val responseData: T,
    val message: String = "",
    val status: Int = STATUS_CODE_OK,
)