package dev.syntext_error.testproject.network

import com.google.gson.GsonBuilder
import dev.syntext_error.testproject.utils.Utilities
import okhttp3.OkHttpClient
import okhttp3.internal.platform.Platform
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.Executors

object RetrofitClient {

    private var retrofit: Retrofit? = null
    private val gson = GsonBuilder().setLenient().create()

    val client: Retrofit
        get() {
            if (retrofit == null) {
                synchronized(Retrofit::class.java) {
                    if (retrofit == null) {

                        retrofit = Retrofit.Builder()
                                .baseUrl(Utilities.ROOT_URL)

                                .addConverterFactory(GsonConverterFactory.create(gson))
                                .build()
                    }
                }

            }
            return retrofit!!
        }



}