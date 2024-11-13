package ac.divan.data.repository

import ac.divan.data.remote.DivanApi
import ac.divan.data.remote.handleApi
import ac.divan.domain.repository.DivanRepository

class DivanRepositoryImpl(
    private val api: DivanApi,
): DivanRepository {

    override suspend fun getFormInfo() = handleApi {
        api.getFormInfo()
    }

    override suspend fun getDefaultMenu(menuSlug: String) = handleApi {
        api.getDefaultMenu(menuSlug)
    }

}