package ac.divan.data.remote

object ApiRoutes {
    const val PATH_FORM_SLUG = "FORM_SLUG"
    const val PATH_BLOCK_SLUG = "BLOCK_SLUG"

    const val GET_SHARED_BOARDS = "v3.0/shared-boards/{$PATH_FORM_SLUG}/"
    const val GET_DEFAULT_MENU = "v3.0/shared-boards/{$PATH_FORM_SLUG}/blocks/{$PATH_BLOCK_SLUG}/"
    const val GET_BLOCK_CONTENT = "v2.0/shared-boards/{$PATH_FORM_SLUG}/blocks/{$PATH_BLOCK_SLUG}/content/"
}