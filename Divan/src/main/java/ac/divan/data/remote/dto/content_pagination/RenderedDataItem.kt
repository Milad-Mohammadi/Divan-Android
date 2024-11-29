package ac.divan.data.remote.dto.content_pagination

import com.google.gson.annotations.SerializedName

data class RenderedDataItem(
    @SerializedName("raw_value") val rawValue: Any?,
    val value: String?,
    val title: String,
    val type: String,
) {
    fun getRawValue(): String? {
        return try {
            rawValue as String
        } catch (e: Exception) {
            null
        }
    }
}