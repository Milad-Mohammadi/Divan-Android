package ac.divan.di

import ac.divan.BuildConfig
import ac.divan.data.remote.DivanApi
import ac.divan.data.remote.interceptors.AdditionalHeadersInterceptor
import ac.divan.data.remote.interceptors.AuthorizationInterceptor
import ac.divan.data.remote.interceptors.ErrorResponseInterceptor
import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level =
            if (BuildConfig.BUILD_TYPE.equals("debug", true)) HttpLoggingInterceptor.Level.BODY
            else HttpLoggingInterceptor.Level.NONE
        return interceptor
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(
        @ApplicationContext context: Context,
        logging: HttpLoggingInterceptor,
    ): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(AdditionalHeadersInterceptor(context))
        .addInterceptor(AuthorizationInterceptor())
        .addInterceptor(ErrorResponseInterceptor(context))
        .addInterceptor(logging)
        .build()

    @Singleton
    @Provides
    fun provideRetrofit(client: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()

    @Singleton
    @Provides
    fun provideDivanApi(retrofit: Retrofit): DivanApi = retrofit.create(DivanApi::class.java)
}