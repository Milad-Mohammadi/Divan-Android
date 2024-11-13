package ac.divan.di

import ac.divan.data.remote.DivanApi
import ac.divan.data.repository.DivanRepositoryImpl
import ac.divan.domain.repository.DivanRepository
import ac.divan.domain.use_case.DivanUseCases
import ac.divan.domain.use_case.GetDefaultMenu
import ac.divan.domain.use_case.GetFormInfo
import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideActivityContext(application: Application): Context = application.applicationContext

    @Provides
    @Singleton
    fun provideDivanRepository(api: DivanApi): DivanRepository {
        return DivanRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun provideActivityUseCases(repository: DivanRepository): DivanUseCases {
        return DivanUseCases(
            getFormInfo = GetFormInfo(repository),
            getDefaultMenu = GetDefaultMenu(repository),
        )
    }
}