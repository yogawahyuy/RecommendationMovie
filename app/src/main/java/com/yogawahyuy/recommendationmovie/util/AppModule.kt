package com.yogawahyuy.recommendationmovie.util

import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun createRetrofitService(retrofit: Retrofit): NetworkService = retrofit.create(NetworkService::class.java)

    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor()=HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }

    @Provides
    @Singleton
    fun provideOKhttpClient(httpLoggingInterceptor: HttpLoggingInterceptor):OkHttpClient =
        OkHttpClient.Builder()
            .readTimeout(180,TimeUnit.SECONDS)
            .connectTimeout(180,TimeUnit.SECONDS)
            .addInterceptor(httpLoggingInterceptor)
            .addInterceptor(AuthInterceptor())
            .build()

    @Provides
    @Singleton
    fun buildRetrofitService(okHttpClient: OkHttpClient):Retrofit{
        val retrofit = Retrofit.Builder()
            .baseUrl(BaseURL().baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .client(okHttpClient)

        return retrofit.build()
    }
}