package ac.divan.data.remote.dto


data class Pagination<T>(
    val objects: List<T>,
    val previous: String?,
    val next: String?,
    val count: Int,
)