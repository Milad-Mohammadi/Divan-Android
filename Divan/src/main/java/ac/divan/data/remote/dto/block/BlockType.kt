package ac.divan.data.remote.dto.block

enum class BlockType(val slug: String) {
    GROUP("group"),
    HEADING("heading"),
    PARAGRAPH("paragraph"),
    GRID_VIEW("grid_view"),
    TABLE("form_result"),
    FORM_CHARTS("form_charts"),
    DROP_DOWN("dropdown"),
    MULTI_SELECT("multiple_select"),
    CHOICE("choice"),
    LINK("link"),
}