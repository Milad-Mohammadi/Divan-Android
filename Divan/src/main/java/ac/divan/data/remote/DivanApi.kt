package ac.divan.data.remote

import ac.divan.data.remote.ApiRoutes.PATH_BLOCK_SLUG
import ac.divan.data.remote.dto.ApiResponse
import ac.divan.data.remote.dto.menu.DefaultMenuContent
import ac.divan.data.remote.dto.menu.DefaultMenuSlug
import retrofit2.http.GET
import retrofit2.http.Path

interface DivanApi {
    @GET(ApiRoutes.GET_SHARED_BOARDS)
    suspend fun getFormInfo(): ApiResponse<DefaultMenuSlug>

    @GET(ApiRoutes.GET_DEFAULT_MENU)
    suspend fun getDefaultMenu(@Path(PATH_BLOCK_SLUG) menuSlug: String): ApiResponse<DefaultMenuContent>
}