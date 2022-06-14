package gini.apps.coin.network

import gini.apps.coin.network.model.CoinModel
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ApiManager {

    @GET("NGHJ3UTW")
    suspend fun coin(): CoinModel

    companion object {
        fun create(): ApiManager {
            val client = OkHttpClient.Builder()
                .addInterceptor(TokenInterceptor())
                .build()

            return Retrofit
                .Builder()
                .client(client)
                .baseUrl("https://pastebin.com/raw/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiManager::class.java)
        }
    }
}


class TokenInterceptor() : Interceptor {

    override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
        var original = chain.request()
        val url = original.url().newBuilder().build()
        original = original.newBuilder().url(url).build()
        return chain.proceed(original)
    }
}


