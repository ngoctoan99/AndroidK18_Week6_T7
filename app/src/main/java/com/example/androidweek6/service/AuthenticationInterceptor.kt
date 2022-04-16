package com.example.androidweek6.service

import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class AuthenticationInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val url : HttpUrl = chain.request().url()
            .newBuilder()
            .addQueryParameter("api_key","56a6350d368119121bafbba6d2b205e0")
            .build()
        val request : Request = chain.request().newBuilder().url(url).build()
        return chain.proceed(request)
    }
}