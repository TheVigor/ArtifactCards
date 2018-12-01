package com.noble.activity.artifactcards.network

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object ArtifactClient {

    private val HOST = "https://playartifact.com/cardset/"

    private var api: ArtifactApi? = null

    fun get(): ArtifactApi? {
        if (api == null) {
            synchronized(ArtifactClient::class.java) {
                if (api == null) {
                    val client = Retrofit.Builder().baseUrl(HOST)
                        .client(HttpClient.getHttpClient())
                        .addConverterFactory(GsonConverterFactory.create())
                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                        .build()
                    api = client.create<ArtifactApi>(ArtifactApi::class.java!!)
                }
            }
        }
        return api
    }
}