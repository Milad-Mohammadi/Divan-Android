package ac.divan.data.remote.dto.board

data class Props(
    val text: String?,
    val type: String?,
    val level: Int?,
    val data: PropsData?
) {
    data class PropsData(
        val slug: String,
        val title: String,
    )
}