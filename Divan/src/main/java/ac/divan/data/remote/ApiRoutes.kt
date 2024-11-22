package ac.divan.data.remote

import ac.divan.BuildConfig

object ApiRoutes {
    const val PATH_BLOCK_SLUG = "BLOCK_SLUG"

    const val GET_SHARED_BOARDS = "v3.0/shared-boards/${BuildConfig.FORM_SLUG}/"
    const val GET_BLOCK = "v2.0/shared-boards/${BuildConfig.FORM_SLUG}/blocks/{$PATH_BLOCK_SLUG}/"
    const val GET_BLOCK_CONTENT = "v2.0/shared-boards/${BuildConfig.FORM_SLUG}/blocks/{$PATH_BLOCK_SLUG}/content/"
}