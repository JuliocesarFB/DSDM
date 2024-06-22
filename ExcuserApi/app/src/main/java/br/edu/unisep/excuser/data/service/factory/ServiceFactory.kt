package br.edu.unisep.excuser.data.service.factory

import br.edu.unisep.excuser.data.service.ExcuseService
import br.edu.unisep.excuser.data.service.config.URL_BASE
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object ServiceFactory {

    private val httpClient = OkHttpClient.Builder()
        .addInterceptor(
            HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY))
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(URL_BASE)
        .client(httpClient)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    fun getExcuseService(): ExcuseService = retrofit.create(ExcuseService::class.java)
}