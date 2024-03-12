package com.yogawahyuy.recommendationmovie.util

import com.yogawahyuy.recommendationmovie.BuildConfig
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class AuthInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val url = chain.request().url.newBuilder()
            .addQueryParameter("api_key",BuildConfig.apiKey).build()
        val authRequest = chain.request().newBuilder().url(url).build()
        return chain.proceed(authRequest)
    }
}