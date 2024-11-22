package ac.divan.data.remote

import ac.divan.data.remote.ApiRoutes.PATH_BLOCK_SLUG
import ac.divan.data.remote.dto.ApiResponse
import ac.divan.data.remote.dto.board.Board
import ac.divan.data.remote.dto.content_pagination.ContentPagination
import ac.divan.data.remote.dto.menu.DefaultMenuSlug
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface DivanApi {
    @GET(ApiRoutes.GET_SHARED_BOARDS)
    suspend fun getFormInfo(): ApiResponse<DefaultMenuSlug>

    @GET(ApiRoutes.GET_DEFAULT_MENU)
    suspend fun getDefaultMenu(@Path(PATH_BLOCK_SLUG) menuSlug: String): ApiResponse<Board>

    @GET(ApiRoutes.GET_BLOCK_CONTENT)
    suspend fun getContentPagination(@Path(PATH_BLOCK_SLUG) slug: String, @Query("page") page: Int, @Query("page_size") pageSize: Int): ApiResponse<ContentPagination>
}