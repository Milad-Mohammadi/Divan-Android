package ac.divan.data.remote.dto.menu

import com.google.gson.Gson

data class Props(
    val text: String?,
    val type: String?,
    val level: Int?,
    val data: Any? // TODO: This is PropsData, but sometimes comes as `"data": "[object Object]", handle it in code`
) {
    data class PropsData(
        val slug: String,
        val title: String,
    )

    fun getData(): PropsData? {
        return try {
            Gson().fromJson(data.toString(), PropsData::class.java)
        } catch (e: Exception) {
            null
        }
    }
}