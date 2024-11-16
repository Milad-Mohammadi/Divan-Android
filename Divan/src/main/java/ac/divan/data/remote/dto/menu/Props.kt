package ac.divan.data.remote.dto.menu

data class Props(
    val text: String?,
    val type: String?,
    val data: Any? // TODO: This is PropsData, but sometimes comes as `"data": "[object Object]", handle it in code`
) {
    data class PropsData(
        val slug: String,
        val title: String,
    )
}