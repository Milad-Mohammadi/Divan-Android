package ac.divan.data.remote.dto.menu

import com.google.gson.annotations.SerializedName

data class FormInfo(
    val board: DefaultMenuBoard,
) {
    data class DefaultMenuBoard(
        @SerializedName("default_menu")
        val defaultMenu: DefaultMenu?
    )

    data class DefaultMenu(
        val slug: String?
    )
}